package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class listAgenda:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_contactos)
        lateinit var editTxtNombre: EditText
        lateinit var editTxtNum: EditText
        lateinit var editTxtApellido: EditText

        editTxtNombre = findViewById(R.id.editTxtNombre)
        editTxtNum = findViewById(R.id.editTextPhone)
        editTxtApellido = findViewById(R.id.editTxtApellido)

        editTxtNum.isEnabled = false

        var listaView: ListView = findViewById(R.id.lista)
        var btnGuardar: ImageButton = findViewById(R.id.guardarDatos)
        var btnEliminar: ImageButton = findViewById(R.id.btnBorrarContacto)
        var btnAgregar: ImageButton = findViewById(R.id.btnAgregarLista)
        var btnVolver: ImageButton = findViewById(R.id.btnVolver)
        var toolbar : Toolbar = findViewById(R.id.toolbar)

        var intent = Intent(this, NuevoContacto::class.java )
        var intentVolver = Intent(this, MainActivity::class.java)


        var sqLmanager = SQLmanager(this)
        var arrayList = sqLmanager.listAgendaAll(this)
        var adapterLista = adapterLista(this, arrayList)


        listaView.adapter = adapterLista

        btnVolver.setImageResource(R.drawable.home)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Mis contactos"

        listaView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem: PersonaClass = arrayList[position]
            editTxtNombre.setText(selectedItem.nombre)
            editTxtNum.setText(selectedItem.telefono)
            editTxtApellido.setText(selectedItem.apellido)

        }

        btnVolver.setOnClickListener{
            startActivity(intentVolver)
        }

        btnGuardar.setOnClickListener {
            val nombre = editTxtNombre.text.toString()
            val apellido = editTxtApellido.text.toString()
            val telefono = editTxtNum.text.toString()

            if (nombre.isNotEmpty() && apellido.isNotEmpty() && telefono.isNotEmpty()) {
                val sqLmanager = SQLmanager(this)
                val persona = PersonaClass(telefono, nombre, apellido)
                val response = sqLmanager.updateAgenda(this, persona, telefono)

                if (response) {
                    arrayList.clear()
                    arrayList.addAll(sqLmanager.listAgendaAll(this))
                    adapterLista.notifyDataSetChanged()

                    editTxtApellido.setText("")
                    editTxtNombre.setText("")
                    editTxtNum.setText("")

                    Toast.makeText(this, "Datos actualizados con éxito", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        btnEliminar.setOnClickListener {
            val telefono = editTxtNum.text.toString()
            if (telefono.isNotEmpty()) {
                val sqLmanager = SQLmanager(this)
                val response = sqLmanager.deleteAgenda(this, telefono)

                if (response) {

                    arrayList.clear()
                    arrayList.addAll(sqLmanager.listAgendaAll(this))
                    adapterLista.notifyDataSetChanged()

                    editTxtApellido.setText("")
                    editTxtNombre.setText("")
                    editTxtNum.setText("")

                    Toast.makeText(this, "Datos eliminados correctamente", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
        btnAgregar.setOnClickListener{
            startActivity(intent)
        }
    }
}


package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar


class NuevoContacto : AppCompatActivity() {
    lateinit var nombre: EditText
    lateinit var apellido:EditText
    lateinit var tel:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_contacto)

        var guardarNuevo: ImageButton =findViewById(R.id.btnAgregarNuevo)
        var volver: ImageButton = findViewById(R.id.btnVolver)
        var toolbar: Toolbar= findViewById(R.id.toolbar)
        val imageView: ImageView= findViewById(R.id.imageView)
        imageView.setImageResource(R.drawable.person_line)


        nombre = findViewById(R.id.txtNombreNuevo)
        apellido=findViewById(R.id.txtApellidoNuevo)
        tel=findViewById(R.id.txtTelNuevo)

        var intentVolver = Intent(this, MainActivity::class.java)
        var intentLista = Intent(this, listAgenda::class.java)

        volver.setImageResource(R.drawable.home)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Nuevo contacto"
        volver.setOnClickListener{
                startActivity(intentVolver)
             }
        guardarNuevo.setOnClickListener {
            if (testDat()){

                var datos= PersonaClass(
                    telefono = tel.text.toString(),
                    nombre = nombre.text.toString(),
                    apellido = apellido.text.toString())
                var sqLmanager = SQLmanager(this)
                var response = sqLmanager.addPersona(this,datos)
                if (response){
                    startActivity(intentLista)
                    Toast.makeText(this,"datos ingresados con exito", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Todos los datos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun testDat():Boolean{
        var response = true
        if (tel.text.isEmpty() || nombre.text.isEmpty() || apellido.text.isEmpty()){
            response =false
        }
        return response
    }
}
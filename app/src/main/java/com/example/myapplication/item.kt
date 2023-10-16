package com.example.myapplication

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class item : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val personSelect: PersonaClass? = intent.getParcelableExtra("PERSONA_SELECCIONADA")

        if (personSelect != null) {

            val editNum: EditText = findViewById(R.id.editTxtPhone)
            val editApellido: EditText = findViewById(R.id.editTxtApellido)
            val editNombre: EditText = findViewById(R.id.editTxtNombre)
            editNombre.setText(personSelect.nombre)
            editApellido.setText(personSelect.apellido)
            editNum.setText(personSelect.telefono)
        }

    }

}
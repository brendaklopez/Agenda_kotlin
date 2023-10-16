package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    var agregarContacto: Button =findViewById(R.id.btnContactoNuevo)
    var listaContactos: Button = findViewById(R.id.btnListaContactos)

        val intent = Intent(this,NuevoContacto::class.java)
        agregarContacto.setOnClickListener{
            startActivity(intent)
        }

        val intentLista = Intent(this,listAgenda::class.java )
        listaContactos.setOnClickListener{
            startActivity(intentLista)
        }
    }

}
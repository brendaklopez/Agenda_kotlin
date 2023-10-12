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
    var intent = Intent(this,NuevoContacto::class.java)
        agregarContacto.setOnClickListener{
            startActivity(intent)
        }
    }

}
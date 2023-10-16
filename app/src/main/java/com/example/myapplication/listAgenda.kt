package com.example.myapplication

import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.selects.select

class listAgenda:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_contactos )

        var listaView: ListView=  findViewById(R.id.lista)
        var sqLmanager = SQLmanager(this)
        var arrayList = sqLmanager.listAgendaAll(this)
        var adapterLista = adapterLista(this, arrayList)

        listaView.adapter = adapterLista

        listaView.setOnItemClickListener{ parent, view, position, id ->
            val selectedItem : PersonaClass = arrayList[position]
            val intent = Intent(this, item::class.java )

            intent.putExtra("PERSONA_SELECCIONADA" , selectedItem)
            startActivity(intent)
        }

        }
    }


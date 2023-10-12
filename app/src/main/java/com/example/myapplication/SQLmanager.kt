package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLmanager(context: Context):SQLiteOpenHelper(context,"agenda.db", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE personas (telefono VARCHAR(10)PRIMARY KEY, nombre VARCHAR(100), apellido(100))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun addPersona(context: Context, datos:PersonaClass):Boolean{
        var response=true
        var contentValues = ContentValues()
        contentValues.put("telefono",datos.telefono)
        contentValues.put("nombre",datos.nombre)
        contentValues.put("apellido",datos.apellido)
        var db= SQLmanager(context)
        var manager = db.writableDatabase
        try {
            manager.insert("personas", null, contentValues)
        }
        catch (e:Exception){
            print(e.message)
            response = false
        }
        finally {
            db.close()
        }
        return response
        fun listAgendaAll(Context)
    }

}
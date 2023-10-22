package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLmanager(private val context: Context):SQLiteOpenHelper(context,"agenda.db", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE personas (telefono VARCHAR(10) PRIMARY KEY, nombre VARCHAR(100), apellido VARCHAR(100))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    companion object {
        fun numDuplicado(context: Context, telefono: String): Boolean {
            val db = SQLmanager(context).readableDatabase
            val cursor = db.rawQuery("SELECT 1 FROM personas WHERE telefono=?", arrayOf(telefono))
            val exists = cursor.moveToFirst()
            cursor.close()
            db.close()
            return exists
        }
    }

        fun addPersona(context: Context, datos: PersonaClass): Boolean {
            var response = true
            var contentValues = ContentValues()
            contentValues.put("telefono", datos.telefono)
            contentValues.put("nombre", datos.nombre)
            contentValues.put("apellido", datos.apellido)
            var db = SQLmanager(context)
            var manager = db.writableDatabase

            try {
                manager.insert("personas", null, contentValues)
            } catch (e: Exception) {
                print(e.message)
                response = false
            } finally {
                db.close()
            }
            return response
        }

        fun listAgendaAll(context: Context): ArrayList<PersonaClass> {
            var arrayList = ArrayList<PersonaClass>()
            var SQL: String = "SELECT * FROM Personas order by nombre"
            var SQLmanager = SQLmanager(context)
            var db = SQLmanager.readableDatabase
            var data = db.rawQuery(SQL, null)
            while (data.moveToNext()) {
                arrayList.add(PersonaClass(data.getString(0), data.getString(1), data.getString(2)))
            }
            return arrayList
        }
        fun countTotalContacts(): Int {
            var count = 0
            var SQL: String = "SELECT COUNT(*) FROM Personas"
            var SQLmanager = SQLmanager(context)
            var db = SQLmanager.readableDatabase
            var cursor = db.rawQuery(SQL, null)

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0)
            }

            cursor.close()
            db.close()
            return count
        }


    fun updateAgenda(context: Context, datos: PersonaClass, telAnterior: String): Boolean {
            var response = true
            var sqlManager = SQLmanager(context)
            var db = sqlManager.writableDatabase
            var array = arrayOf(telAnterior)
            var contentValues = ContentValues()
            contentValues.put("telefono", datos.telefono)
            contentValues.put("nombre", datos.nombre)
            contentValues.put("apellido", datos.apellido)
            /*if (SQLmanager.numDuplicado(context, datos.telefono)) {
                return false
            }*/
            try {
                db.update("personas", contentValues, "telefono like ?", array)
            } catch (e: Exception) {
                e.printStackTrace()
                response = false
            } finally {
                db.close()
            }

            return response
        }
    fun deleteAgenda(context: Context, telefono: String):Boolean{
        val db = this.writableDatabase
        val whereClause = "telefono = ?"
        val whereArgs = arrayOf(telefono)

        return try {
            db.delete("personas", whereClause, whereArgs) > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            db.close()
        }
    }
}
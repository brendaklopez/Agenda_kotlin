package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
class PersonaClass {
    var telefono: String =""
    var nombre:String=""
    var apellido:String=""

    constructor(telefono: String, nombre: String, apellido: String) {
        this.telefono = telefono
        this.nombre = nombre
        this.apellido = apellido
    }
}
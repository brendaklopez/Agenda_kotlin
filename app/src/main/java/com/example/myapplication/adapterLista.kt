package com.example.myapplication
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.intellij.lang.annotations.JdkConstants.TitledBorderTitlePosition

class adapterLista : BaseAdapter {
    var context: Context? = null
    var arrayList = ArrayList<PersonaClass>()

    constructor(context: Context?, arrayList: ArrayList<PersonaClass>) : super() {
        this.context = context
        this.arrayList = arrayList
    }

    override fun getCount(): Int {

        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup? ) {
        var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var row= inflater.inflate(R.layout.lista_contactos,null)
        var nombre = row.findViewById<TextView>(R.id.ListNombres)
        var apellido = row.findViewById<TextView>(R.id.listApellidos)
        var telefono = row.findViewById<TextView>(R.id.ListTelefonos)
        nombre.text = arrayList[position].nombre
        apellido.text = arrayList[position].apellido
        telefono.text = arrayList[position].telefono
        return row
    }

}
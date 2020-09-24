package com.example.businessbuilder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class BusinessEmployee(private var fullName: String, private var position: String, private var date: String){

    override fun toString(): String {
        return "$fullName-$position-$date"
    }
    fun getFullName(): String{
        return fullName
    }
    fun getPosition(): String{
        return position
    }
    fun getDate():String{
        return date
    }
    init{
        this.fullName = fullName
        this.position = position
        this.date = date
    }
}

class BusinessAdapter(context: Context, list: ArrayList<BusinessEmployee>) :
    ArrayAdapter<Movie?>(context, 0, list as List<Movie?>) {
    private var mContext: Context
    private var employeeList: List<BusinessEmployee> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.users_table, parent, false)
        val currentEmployee = employeeList[position]
        val text1: TextView = listItem?.findViewById(R.id.name1) as TextView
        text1.text = currentEmployee.getFullName()
        val name = listItem.findViewById(R.id.name2) as TextView
        name.text = currentEmployee.getPosition()
        val release = listItem.findViewById(R.id.name3) as TextView
        release.text = currentEmployee.getDate()
        return listItem
    }

    init {
        employeeList = list
        mContext = context;
    }
}

class BusinessEmployees : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_employees)

        val listView = findViewById<ListView>(R.id.listView2)
        val employeeList : ArrayList<BusinessEmployee> = ArrayList()

        val given = intent.getStringExtra("autoComplete")
        val nameForLabel = intent.getStringExtra("nameBusiness")
        this.title = nameForLabel

        MySingleton.getInstance(this).businessEmployees(given, listView, employeeList, this)
    }
}
package com.example.businessbuilder

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import org.w3c.dom.Text




class TaskMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_menu)

        val given = intent.getStringExtra("autoComplete")
        val nameBusiness = intent.getStringExtra("nameBusiness")

        val listView = findViewById<ListView>(R.id.listView20)
        val employeeList : ArrayList<BusinessEmployee> = ArrayList()

        this.title = "$nameBusiness Tasks"

        //MySingleton.getInstance(this).businessEmployees(given, listView, employeeList, this)
    }
}
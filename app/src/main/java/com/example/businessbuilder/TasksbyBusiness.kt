package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class TasksbyBusiness : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasksby_business)

        val taskList: ArrayList<TaskMenuList> = ArrayList()
        val adapter1 = TaskMenuAdapter(this, taskList)
        val list : ListView = findViewById(R.id.listView3)

        MySingleton.getInstance(this).getMoop(this, list, taskList )

        list.setOnItemClickListener { parent, view, position, id ->
            val element = adapter1.getItem(position)
            val intent = Intent(this, TaskMenu::class.java)
            intent.putExtra("autoComplete",element?.getUserName())
            intent.putExtra("nameBusiness", element?.getmName())
            intent.putExtra("nameForOtherTaskMenu", element?.getmName())
            startActivity(intent)
        }
    }
}
package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MyTasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks)

        val tasksList : ArrayList<TasksInBusiness> = ArrayList()
        val adapter1 = TaskAdapter(this, tasksList)

        val listView = findViewById<ListView>(R.id.listView200)
        MySingleton.getInstance(this).getTasksForUsers(listView, this, tasksList)


        listView.setOnItemClickListener { parent, view, position, id ->
            val element = adapter1.getItem(position)
            val intent = Intent(this, ViewTasks::class.java)
            intent.putExtra("whichMenu", "MyTasks")
            intent.putExtra("id",element?.getTaskId())
            intent.putExtra("status", element?.getTaskStatus1())
            intent.putExtra("title", element?.getTaskTitle())
            intent.putExtra("category", element?.getTaskType())
            startActivity(intent)
        }
    }
}
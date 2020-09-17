package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView

class TasksDelegated : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_delegated)

        this.title = "Delegated Tasks"

        val listView = findViewById<ListView>(R.id.listView21)
        val tasksList : ArrayList<TasksInBusiness> = ArrayList()
        val adapter1 = TaskAdapter(this, tasksList)

        MySingleton.getInstance(this).getDelegatedTasks(listView, this, tasksList)

        listView.setOnItemClickListener { parent, view, position, id ->
            val element = adapter1.getItem(position)
            val intent = Intent(this, ViewTaskDelegated::class.java)
            intent.putExtra("id",element?.getTaskId())
            intent.putExtra("status", element?.getTaskStatus1())
            intent.putExtra("title", element?.getTaskTitle())
            intent.putExtra("category", element?.getTaskType())
            startActivity(intent)
        }
    }
}
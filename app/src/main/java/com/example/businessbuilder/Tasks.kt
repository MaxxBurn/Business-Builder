package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Tasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val addTasks = findViewById<ImageButton>(R.id.addTask)




        addTasks.setOnClickListener {
            val intent = Intent(this, AddTasksMenu::class.java)
            startActivity(intent)
        }
    }
}
package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView

class Tasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val addTasks = findViewById<ImageButton>(R.id.addTask)
        val  moviesList: ArrayList<Movie> = ArrayList()
        val adapter1 = MovieAdapter(this, moviesList)
        val list : ListView = findViewById(R.id.listView3)


        MySingleton.getInstance(this).getMoop(this, list, moviesList )

        list.setOnItemClickListener { parent, view, position, id ->

        }

        addTasks.setOnClickListener {
            val intent = Intent(this, AddTasksMenu::class.java)
            startActivity(intent)
        }
    }
}
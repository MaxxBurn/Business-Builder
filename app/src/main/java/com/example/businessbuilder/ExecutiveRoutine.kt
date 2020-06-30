package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ExecutiveRoutine : AppCompatActivity() {

    lateinit var button1: ImageButton
    lateinit var button2: ImageButton
    lateinit var button3: ImageButton
    lateinit var button4: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_executive_routine)

        button1 = findViewById(R.id.toDoListButton)
        button2 = findViewById(R.id.businessJournalButton)
        button3 = findViewById(R.id.notesButton)
        button4 = findViewById(R.id.performanceSnapButton)

        button1.setOnClickListener {
            val intent = Intent(this,  AddToDoList::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, AddBusinessJournal::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(this, AddNotes::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this, AddPerformanceSnap::class.java)
            startActivity(intent)
        }



    }
}
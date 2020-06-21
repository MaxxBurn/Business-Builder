package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

lateinit var button : ImageButton

class PlanningAction1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning_action1)

        button = findViewById(R.id.objDetails)
        button.setOnClickListener {
            val openObjectives = Intent(this, ObjectiveDetails::class.java)
            startActivity(openObjectives)
        }

    }
}
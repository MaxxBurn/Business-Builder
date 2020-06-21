package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PlanningAction2 : AppCompatActivity() {

    lateinit var button2: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning_action2)
        button2 = findViewById(R.id.milestone)
        button2.setOnClickListener {
            val view = Intent(this, MileStone::class.java)
            startActivity(view)
        }

    }
}
package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

lateinit var saveButton: ImageButton


class ObjectiveDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objective_details)
        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
        //Implement logic



            val saved = Intent(this, PlanningAction1::class.java)
            startActivity(saved)
        }



    }
}
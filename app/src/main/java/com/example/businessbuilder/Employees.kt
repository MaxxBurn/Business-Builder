package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Employees : AppCompatActivity() {
    lateinit var button1: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees)

        button1 = findViewById(R.id.addEmployee)
        button1.setOnClickListener {
            val intent = Intent(this, AddEmployee::class.java)
            startActivity(intent)
        }
    }
}
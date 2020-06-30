package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Other : AppCompatActivity() {
    lateinit var addButton : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        addButton = findViewById(R.id.addOther)
        addButton.setOnClickListener {
            val intent = Intent(this, AddOther::class.java)
            startActivity(intent)
        }
    }
}
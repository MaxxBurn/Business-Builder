package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StrategicPartners : AppCompatActivity() {
    lateinit var addButton : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_strategic_partners)

        addButton = findViewById(R.id.addStrategicPartner)
        addButton.setOnClickListener {
            val intent = Intent(this, AddStrategicPartners::class.java)
            startActivity(intent)
        }
    }
}
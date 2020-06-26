package com.example.businessbuilder

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class KeyPeople : AppCompatActivity() {

    lateinit var button1: ImageButton
    lateinit var button2: ImageButton
    lateinit var button3: ImageButton
    lateinit var button4: ImageButton
    lateinit var button5: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_people)

        button1 = findViewById(R.id.employees)
        button2 = findViewById(R.id.customersDebtors)
        button3 = findViewById(R.id.suppliersCreditors)
        button4 = findViewById(R.id.strategicPartners)
        button5 = findViewById(R.id.other)

        button1.setOnClickListener {
            val intent = Intent(this, Employees::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, CustomersDebtors::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {

        }
        button4.setOnClickListener {

        }
        button5.setOnClickListener {

        }
    }
}
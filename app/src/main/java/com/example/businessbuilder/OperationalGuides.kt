package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class OperationalGuides : AppCompatActivity() {

    lateinit var button1 : ImageButton
    lateinit var button2 : ImageButton
    lateinit var button3 : ImageButton
    lateinit var button4 : ImageButton
    lateinit var button5 : ImageButton
    lateinit var button6 : ImageButton
    lateinit var button7 : ImageButton
    lateinit var button8 : ImageButton
    lateinit var button9 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operational_guides)

        button1  = findViewById(R.id.personalDev)
        button2 = findViewById(R.id.commun1)
        button3 = findViewById(R.id.commun2)
        button4 = findViewById(R.id.sales1)
        button5 = findViewById(R.id.sales2)
        button6 = findViewById(R.id.general1)
        button7 = findViewById(R.id.general2)
        button8 = findViewById(R.id.general3)
        button9 = findViewById(R.id.strat1)

        button1.setOnClickListener {
            val intent = Intent(this, PersonalDev::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {

        }
        button3.setOnClickListener {

        }
        button4.setOnClickListener {

        }
        button5.setOnClickListener {

        }
        button6.setOnClickListener {

        }
        button7.setOnClickListener {

        }
        button8.setOnClickListener {

        }
        button9.setOnClickListener {

        }
    }
}
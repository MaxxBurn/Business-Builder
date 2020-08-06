package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PlanningAction : AppCompatActivity() {

    lateinit var button1 : ImageButton
    lateinit var button2 : ImageButton
    lateinit var button3 : ImageButton
    lateinit var button4 : ImageButton
    lateinit var button5 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planningaction)

        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        button5 = findViewById(R.id.btn5)


        button1.setOnClickListener{
            val button1 = Intent(this,PlanningAction1::class.java)
            startActivity(button1)
        }
        button2.setOnClickListener{
            val button2 = Intent(this,PlanningAction2::class.java)
            startActivity(button2)
        }
        button3.setOnClickListener{
            val button3 = Intent(this,PlanningAction3::class.java)
            startActivity(button3)
        }
        button4.setOnClickListener{
            val button4 = Intent(this,PlanningAction4::class.java)
            startActivity(button4)
        }
        button5.setOnClickListener{
            val button5 = Intent(this,PlanningAction5::class.java)
            startActivity(button5)
        }
    }



}
package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toolbar




class DailyTransaction : AppCompatActivity() {
    lateinit var button1: ImageButton
    lateinit var button3: ImageButton
    lateinit var button4: ImageButton
    lateinit var button5: ImageButton
    lateinit var button6: ImageButton
    lateinit var button2: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transaction)


        button2 = findViewById(R.id.income)
        button1 = findViewById(R.id.creditSales)
        button3 = findViewById(R.id.payment)
        button4 = findViewById(R.id.cashBook)
        button5 = findViewById(R.id.production)
        button6 = findViewById(R.id.assetRegister)


        button2.setOnClickListener {
            val button2 = Intent(this,DailyTransaction2::class.java)
            startActivity(button2)
        }
        button1.setOnClickListener {
            val button1= Intent(this, DailyTransaction1::class.java)
            startActivity(button1)
        }
        button3.setOnClickListener{
            val button3 = Intent(this, RequestBudget::class.java)
            startActivity(button3)
        }
        button4.setOnClickListener {
            val button4 = Intent(this, DailyTranscation4::class.java)
            startActivity(button4)
        }
        button5.setOnClickListener{
            val button5 = Intent(this,DailyTransaction5::class.java)
            startActivity(button5)
        }
        button6.setOnClickListener {
            val button6 = Intent(this, DailyTransaction6::class.java)
            startActivity(button6)
        }

    }
}

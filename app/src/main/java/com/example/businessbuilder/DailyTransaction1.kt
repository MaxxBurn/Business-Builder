package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DailyTransaction1 : AppCompatActivity() {


    lateinit var button1: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transaction1)
            button1 = findViewById(R.id.invoiceButton)
            button1.setOnClickListener {
                val invoiceMenu = Intent(this,Invoice::class.java)
                startActivity(invoiceMenu)
            }
    }
}
package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DailyTransaction3 : AppCompatActivity() {

    lateinit var openPaymentButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transaction3)

        openPaymentButton = findViewById(R.id.openPayment)
        openPaymentButton.setOnClickListener {
            val open = Intent(this, Payment::class.java)
            startActivity(open)
        }

    }
}
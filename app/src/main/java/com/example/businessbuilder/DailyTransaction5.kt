package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DailyTransaction5 : AppCompatActivity() {

    lateinit var openProduction: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transaction5)

        openProduction = findViewById(R.id.openProductionButton)
        openProduction.setOnClickListener {
            val act = Intent(this,Production::class.java)
            startActivity(act)
        }
    }
}
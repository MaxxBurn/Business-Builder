package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CustomersDebtors : AppCompatActivity() {

    lateinit var searchButton: FloatingActionButton
    lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customers_debtors)

        searchButton = findViewById(R.id.searchButtonCustomers)
        addButton = findViewById(R.id.addButtonCustomers)

        searchButton.setOnClickListener {

        }
        addButton.setOnClickListener {
            val intent = Intent(this, CustomerDebtorsMenu::class.java)
            startActivity(intent)
        }

    }
}
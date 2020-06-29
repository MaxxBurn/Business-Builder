package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SuppliersCreditors : AppCompatActivity() {

    lateinit var addButton : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suppliers_creditors)

        addButton = findViewById(R.id.addCreditsSuppliers)

        addButton.setOnClickListener {
            val intent = Intent(this, AddCreditorsSuppliers::class.java)
            startActivity(intent)
        }

    }
}
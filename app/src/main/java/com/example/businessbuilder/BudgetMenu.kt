package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class BudgetMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_menu)

        val lookAtBudgetRequests = findViewById<ImageButton>(R.id.budgetRequestImage)
        val requestBudget = findViewById<ImageButton>(R.id.requestBudget)
        lookAtBudgetRequests.setOnClickListener {
            val intent = Intent(this, BudgetRequestList::class.java)
            startActivity(intent)
        }
        requestBudget.setOnClickListener {
            val intent = Intent(this, RequestBudget::class.java)
            startActivity(intent)
        }

    }
}
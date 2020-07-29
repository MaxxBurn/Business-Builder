package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Resources : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)

        val registrationMenu = findViewById<ImageButton>(R.id.registrationMenu)
        val editUsers = findViewById<ImageButton>(R.id.editUsers)
        val lookAtBudgetRequests = findViewById<ImageButton>(R.id.budgetRequestImage)
        registrationMenu.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        editUsers.setOnClickListener {
            val intent = Intent(this, EditUsersMenuList::class.java)
            startActivity(intent)
        }
        lookAtBudgetRequests.setOnClickListener {
            val intent = Intent(this, BudgetRequestList::class.java)
            startActivity(intent)
        }

    }
}
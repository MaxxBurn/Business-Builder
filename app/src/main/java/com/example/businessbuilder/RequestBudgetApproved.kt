package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class RequestBudgetApproved : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_budget_approved)

        val yeet = mutableListOf<CharSequence>()
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, yeet)
        val list1: ListView = findViewById(R.id.budgetListApproved)

        MySingleton.getInstance(this).getBudgetApproved(list1,this,yeet,adapter)
        list1.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            val intent = Intent(this, EditBudgetMenu::class.java)
            intent.putExtra("autoComplete", element)
            startActivity(intent)
        }
    }
}
package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class RequestBudgetReceived : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_budget_received)

        val yeet = mutableListOf<CharSequence>()
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, yeet)
        val list1: ListView = findViewById(R.id.budgetListReceived)

        if(SESSION_STATUS != "Administrator"){
            MySingleton.getInstance(this).getBudgetReceived1(list1,this,yeet,adapter)
        }
        else{
            MySingleton.getInstance(this).getBudgetReceived(list1,this,yeet,adapter)
        }

        list1.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            val intent = Intent(this, EditBudgetMenu::class.java)
            intent.putExtra("autoComplete", element)
            startActivity(intent)
        }


    }
}
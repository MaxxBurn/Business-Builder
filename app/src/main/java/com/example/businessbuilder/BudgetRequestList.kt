package com.example.businessbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class BudgetRequestList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_request_list)

        val yeet = mutableListOf<String>()
        val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, yeet)
        val list: ListView = findViewById(R.id.userList)

        //MySingleton.getInstance(this).getBudgetList(list,this,yeet,adapter)


    }
}
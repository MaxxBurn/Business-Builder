package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.text.HtmlCompat

data class BudgetList(val id: String, val name: String, val business: String, val amount: String, val context: Context){
    fun toString1(): CharSequence {
        val text = TextView(context)
        val yeet = id.toInt() - 915
        val html: String = "<b>" +yeet +". "+ name +"</b>" + " - " + business + " - " + amount + "€"
        text.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        return text.text
    }
}

class BudgetRequestList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_request_list)

        val yeet = mutableListOf<CharSequence>()
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, yeet)
        val list1: ListView = findViewById(R.id.budgetList)

        MySingleton.getInstance(this).getBudgetRequestList(list1,this,yeet,adapter)
        list1.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            val intent = Intent(this, EditBudgetMenu::class.java)
            intent.putExtra("autoComplete", element)
            startActivity(intent)
        }

    }
}
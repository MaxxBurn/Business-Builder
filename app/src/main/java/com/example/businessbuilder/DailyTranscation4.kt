package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


data class addCashBook( val date: String, var description: String, var reference: String, var department: String, var cashIn: Int, var cashOut: Int, var balance: Int) {
    override fun toString(): String {
        return "${this.date}\n${this.description}\n${this.reference}\n${this.department}\n${this.cashIn}\n${this.cashOut}\n${this.balance} "
    }
}

class DailyTranscation4 : AppCompatActivity() {

    lateinit var addCashBook: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transcation4)

        val yeet = mutableListOf<String>()
        val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, yeet)
        val list: ListView = findViewById(R.id.listtest)

        MySingleton.getInstance(this).getData14(list,this,yeet,adapter)

        list.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            val intent = Intent(this, Cashbook::class.java)
            intent.putExtra("autoComplete", element)
            startActivity(intent)
        }
        addCashBook = findViewById(R.id.cashBookAddButton)
        addCashBook.setOnClickListener {
            val intent = Intent(this,Cashbook::class.java)
            startActivity(intent)
        }
    }

}
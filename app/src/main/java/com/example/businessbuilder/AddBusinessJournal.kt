package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddBusinessJournal : AppCompatActivity() {

    lateinit var button1 : FloatingActionButton
    lateinit var button2 : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_business_journal)

        button1 = findViewById(R.id.businessSearchButton)
        button2 = findViewById(R.id.businessAddButton)

        button2.setOnClickListener {
            val intent = Intent(this, BusinessJournalMenu::class.java)
            startActivity(intent)
        }
        button1.setOnClickListener {
            //Search
        }
    }
}
package com.example.businessbuilder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton


open class DailyTransaction2 : AppCompatActivity() {
    lateinit var searchButton:FloatingActionButton
    lateinit var addButton: FloatingActionButton
    lateinit var income: TextView
    lateinit var searchBar: EditText
    lateinit var exitSearch: FloatingActionButton
    lateinit var constraintLayour: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transaction2)

        searchButton = findViewById(R.id.searchButtonCustomers)
        addButton = findViewById(R.id.addButton)
        income = findViewById(R.id.income)
        searchBar = findViewById(R.id.searchBar)
        exitSearch = findViewById(R.id.exitSearch)
        constraintLayour = findViewById(R.id.constraintLayout2)



        searchButton.setOnClickListener {
            addButton.hide()
            income.visibility = View.INVISIBLE
            searchBar.visibility = View.VISIBLE
            exitSearch.show()
        }
        exitSearch.setOnClickListener{
            addButton.show()
            income.visibility = View.VISIBLE
            searchBar.visibility = View.INVISIBLE
            exitSearch.hide()
        }
        addButton.setOnClickListener {
            val addIncome = Intent(this,AddIncomeMenu::class.java)
            startActivityForResult(addIncome,3)
        }

    }

}
package com.example.businessbuilder

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class AddIncomeMenu : DailyTransaction2(), DatePickerDialog.OnDateSetListener {
    lateinit var date: TextView
    lateinit var button: FloatingActionButton
    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0



    override fun onCreate(savedInstanceState: Bundle?) {

        //Creating buttons + adding onClickListeners
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income_menu)
        date = findViewById(R.id.date)
        button = findViewById(R.id.saveButton)

        button.setOnClickListener {

        }
        pickDate()


    }

    private fun pickDate(){
        date.setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this,this,year,month,day).show()
        }
    }
    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            savedDay = dayOfMonth
            savedMonth = month
            savedYear = year

            getDateTimeCalendar()
            date.text = "$savedMonth-$savedDay-$savedYear"

        }



}
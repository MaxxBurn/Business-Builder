package com.example.businessbuilder

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_tasks_menu.*
import kotlinx.android.synthetic.main.activity_request_budget.*
import java.util.*

class AddTasksMenu : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    var isClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tasks_menu)

        val startDate = findViewById<TextView>(R.id.startDate)
        val finishDate = findViewById<TextView>(R.id.finishDate)

        pickDate()
    }

    private fun pickDate(){
        startDate.setOnClickListener {
            isClicked = false
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()
        }
        finishDate.setOnClickListener {
            isClicked = true
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
        if (isClicked == false) {
            startDate.text = "$savedMonth-$savedDay-$savedYear"
        } else {
            finishDate.text = "$savedMonth-$savedDay-$savedYear"
        }
    }
}
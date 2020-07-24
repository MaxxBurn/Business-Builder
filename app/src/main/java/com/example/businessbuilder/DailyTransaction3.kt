package com.example.businessbuilder

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.DateKeyListener
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_daily_transaction3.*
import java.util.*

data class GetUsersName(val name: String){
    override fun toString(): String {
        return "${this.name}"
    }
}

class DailyTransaction3 : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0


    lateinit var openPaymentButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transaction3)



        val datePicker = findViewById<TextView>(R.id.datePicker)
        pickDate()

        //First Spinner
        val spinner1 = findViewById<Spinner>(R.id.spinner)
        var nameList = mutableListOf<String>()
        nameList.add("Select User...")
        val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList)
        spinner1.adapter = adapter
        MySingleton.getInstance(this).getUsersNames(spinner1, adapter, nameList)

        //Second Spinner
        val spinner2 = findViewById<Spinner>(R.id.spinner2)
        var nameList2 = mutableListOf<String>()
        nameList2.add("Select Business...")
        val adapter2: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList2)
        spinner2.adapter = adapter2
        MySingleton.getInstance(this).getBusinessNames(spinner2, adapter2, nameList2)

        //Third Spinner
        val spinner3 = findViewById<Spinner>(R.id.spinner3)
        var nameList3 = mutableListOf<String>()
        nameList3.add("Select Reason...")
        val adapter3: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList3)
        spinner3.adapter = adapter3
        MySingleton.getInstance(this).getReason(spinner3, adapter3, nameList3)

        //Forth Spinner
        val spinner4 = findViewById<Spinner>(R.id.spinner4)
        var nameList4 = mutableListOf<String>()
        nameList4.add("Select Giver...")
        nameList4.add("Arta Petaj")
        nameList4.add("Mariglen Lazaj")
        nameList4.add("Flladi Malaj")
        val adapter4: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList4)
        spinner4.adapter = adapter4
    }
    private fun pickDate(){
        datePicker.setOnClickListener {
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
        datePicker.text = "$savedMonth-$savedDay-$savedYear"
    }
}
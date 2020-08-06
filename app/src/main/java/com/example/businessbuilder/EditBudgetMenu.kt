package com.example.businessbuilder

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_request_budget.*
import kotlinx.android.synthetic.main.activity_edit_budget_menu.*
import java.util.*

class EditBudgetMenu : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var day = 0
    var month = 0
    var year = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_budget_menu)
        val given = intent.getCharSequenceExtra("autoComplete").toString()
        pickDate()

        var keepinMind: Int = 0
        var count: Int = 0
        var id: String = ""
        var reason: String = ""
        var amount: String  = ""
        var spaceCount: Int = 0
        for(i in keepinMind until given.length-1){
            if(given[i] == '.'){
                count++
                keepinMind += 2
                continue
            }
            if(given[i] == '-'){
                count++
                keepinMind += 2
                continue
            }
            if(given[i] == ' '){
                continue
            }
            if(count ==0){
                id += given[i]
            }
            else if(count == 3) {
                amount += given[i]
            }
        }
        val rId: Int = id.toInt() // + 915
        val textAmount = findViewById<EditText>(R.id.editTextTextPersonName19)
        val autoGiver = findViewById<TextView>(R.id.autoGiver)
        val title = findViewById<EditText>(R.id.textView217)
        val description = findViewById<EditText>(R.id.editTextTextMultiLine2)
        val date1 = findViewById<TextView>(R.id.datePicker1)

        MySingleton.getInstance(this).getGiver(rId, autoGiver, title, description)
        textAmount.setText(amount)
        val spinner5 = findViewById<Spinner>(R.id.spinner5)
        var nameList5 = mutableListOf<String>()
        nameList5.add("Select Reason...")
        val adapter5: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList5)
        spinner5.adapter = adapter5
        MySingleton.getInstance(this).getReason(spinner5, adapter5, nameList5)

        val updateButton = findViewById<Button>(R.id.button4)

        updateButton.setOnClickListener {

            val inputText = "Please fill out all the forms!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, inputText, duration)
            if(textAmount.text.toString() == "" || spinner5.selectedItem.toString() == "Select Reason..." ){
                toast.show()
            }
            else{
                MySingleton.getInstance(this).updateBudget(rId, textAmount.text.toString(), title.text.toString(), date1.text.toString(),
                description.text.toString(), spinner5.selectedItem.toString())
                val inputText2 = "Updated Successfully!"
                val toast2 = Toast.makeText(applicationContext, inputText2, duration)
                toast2.show()
                val intent = Intent(this, BudgetMenu::class.java)
                startActivity(intent)
            }
        }

    }

    private fun pickDate() {
        datePicker1.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    private fun getDateTimeCalendar() {
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
        datePicker1.text = "$savedYear-$savedMonth-$savedDay"
    }
}
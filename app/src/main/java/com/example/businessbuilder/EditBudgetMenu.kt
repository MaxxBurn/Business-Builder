package com.example.businessbuilder

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_edit_budget_menu.*
import kotlinx.android.synthetic.main.activity_request_budget.*
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
        val reasonText = findViewById<TextView>(R.id.reasonText)

        MySingleton.getInstance(this).getGiver(rId, autoGiver, title, description)

        textAmount.setText(amount)

        MySingleton.getInstance(this).getSpecificReason(rId, reasonText)

        val spinner6 = findViewById<Spinner>(R.id.spinnerStatus)
        var nameList6 = mutableListOf<String>()
        nameList6.add("Select Status...")
        nameList6.add("Pending")
        nameList6.add("Unapproved")
        nameList6.add("Approved")
        nameList6.add("Received")
        val adapter6: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList6)
        spinner6.adapter = adapter6

        val reasonRejectText = findViewById<EditText>(R.id.reasonRejectText)
        val reasonReject = findViewById<TextView>(R.id.textView221)

        spinner6.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(spinner6.selectedItem.toString() == "Unapproved"){
                    reasonReject.visibility = VISIBLE
                    reasonRejectText.visibility = VISIBLE
                }
                else{
                    reasonReject.visibility = GONE
                    reasonRejectText.setText("")
                    reasonRejectText.visibility = GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val updateButton = findViewById<Button>(R.id.button4)
        val editButton = findViewById<FloatingActionButton>(R.id.editStuff)
        val viewButton = findViewById<FloatingActionButton>(R.id.viewStuff)

        title.isEnabled = false
        textAmount.isEnabled = false
        description.isEnabled = false
        spinner6.isEnabled = false
        reasonText.isEnabled = false
        autoGiver.isEnabled = false
        date1.isEnabled = false
        reasonRejectText.isEnabled = false

        editButton.setOnClickListener {
            title.isEnabled = true
            textAmount.isEnabled = true
            description.isEnabled = true
            spinner6.isEnabled = true
            reasonText.isEnabled = true
            autoGiver.isEnabled = true
            date1.isEnabled = true
            reasonRejectText.isEnabled = true
            editButton.hide()
            viewButton.show()
        }

        viewButton.setOnClickListener {
            title.isEnabled = false
            textAmount.isEnabled = false
            description.isEnabled = false
            spinner6.isEnabled = false
            reasonText.isEnabled = false
            reasonRejectText.isEnabled = false
            autoGiver.isEnabled = false
            date1.isEnabled = false
            editButton.show()
            viewButton.hide()
        }
        updateButton.setOnClickListener {
            val inputText = "Please fill out all the forms!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, inputText, duration)
            if(textAmount.text.toString() == "" || spinner6.selectedItem.toString() == "Select Status..."){
                toast.show()
            }
            else if((spinner6.selectedItem == "Unapproved" && reasonRejectText.text.toString() == "") || !title.isEnabled){
                toast.show()
            }
            else{
                MySingleton.getInstance(this).updateBudget(rId, textAmount.text.toString(), title.text.toString(), date1.text.toString(),
                description.text.toString(), reasonRejectText.text.toString(), spinner6.selectedItem.toString())
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
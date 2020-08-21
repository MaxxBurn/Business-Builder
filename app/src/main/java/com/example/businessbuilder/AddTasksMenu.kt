package com.example.businessbuilder

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.*
import android.widget.AdapterView.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
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
        val spinner5 = findViewById<Spinner>(R.id.spinner5)
        val spinner6 = findViewById<Spinner>(R.id.spinner6)
        val taskTitle = findViewById<EditText>(R.id.taskTitle)
        val priority = findViewById<RadioGroup>(R.id.radioGroup5)
        val delegated = findViewById<EditText>(R.id.editTextTextPersonName20)
        val comment = findViewById<EditText>(R.id.editTextTextMultiLine4)

        var nameList5 = mutableListOf<String>()
        nameList5.add("Select Users...")
        val adapter5: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList5)
        spinner5.adapter = adapter5
        MySingleton.getInstance(this).getUsersNames(spinner5, adapter5, nameList5, SESSION_STATUS)

        var nameList6 = mutableListOf<String>()
        nameList6.add("Select Businesses...")
        val adapter6: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList6)
        spinner6.adapter = adapter6
        MySingleton.getInstance(this).getBusinessNames(spinner6, adapter6, nameList6)

        val chipgroup1 = findViewById<ChipGroup>(R.id.chipGroup)
        val chipgroup2 = findViewById<ChipGroup>(R.id.chipGroup2)

        spinner5?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var temp = false
                for(i in 0 until chipgroup1.childCount){
                    val chippo2 = chipgroup1.getChildAt(i) as Chip
                    if(chippo2.text.toString() == spinner5.selectedItem.toString()){
                        temp = true
                        break
                    }
                }
                if(spinner5.selectedItem.toString() == "Select Users..." ){ }
                else if(temp == true){
                    temp == false
                }
                else{
                val chip = layoutInflater.inflate(R.layout.single_chip_layout, chipgroup1,false) as Chip
                chip.text = spinner5.selectedItem.toString()
                    chip.isClickable = true
                chipgroup1.addView(chip)
                }
                if(chipgroup1.isEmpty()){
                    chipgroup1.visibility = GONE
                }
                else if (chipgroup1.isNotEmpty()){
                    chipgroup1.visibility = VISIBLE
                }
            }
        }
        //Chips 6
        spinner6?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var temp = false
                for(i in 0 until chipgroup2.childCount){
                    val chippo2 = chipgroup2.getChildAt(i) as Chip
                    if(chippo2.text.toString() == spinner6.selectedItem.toString()){
                        temp = true
                        break
                    }
                }
                if(spinner6.selectedItem.toString() == "Select Businesses..." ){ }
                else if(temp == true){
                    temp == false
                }
                else{
                    val chip = layoutInflater.inflate(R.layout.single_chip_layout, chipgroup2,false) as Chip
                    chip.text = spinner6.selectedItem.toString()
                    chip.isClickable = true
                    chipgroup2.addView(chip)
                }
                if(chipgroup2.isEmpty()){
                    chipgroup2.visibility = GONE
                }
                else if (chipgroup2.isNotEmpty()){
                    chipgroup2.visibility = VISIBLE
                }
            }
        }
        chipgroup1.setOnCheckedChangeListener { chipGroup, i ->
            val chippo2: Chip = chipgroup1.findViewById(chipGroup.checkedChipId) as Chip
            chipgroup1.removeView(chippo2)

            if(chipgroup1.isEmpty()){
                chipgroup1.visibility = GONE
            }
            else if (chipgroup1.isNotEmpty()){
                chipgroup1.visibility = VISIBLE
            }
        }

        chipgroup2.setOnCheckedChangeListener { chipGroup, i ->
            val chippo3: Chip = chipgroup2.findViewById(chipGroup.checkedChipId) as Chip
            chipgroup2.removeView(chippo3)

            if(chipgroup2.isEmpty()){
                chipgroup2.visibility = GONE
            }
            else if (chipgroup2.isNotEmpty()){
                chipgroup2.visibility = VISIBLE
            }
        }

        var nameList7 = mutableListOf<String>()
        nameList7.add("Select Category...")
        nameList7.add("Daily")
        nameList7.add("Monthly Requested")
        nameList7.add("Monthly Required")
        val adapter7: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, nameList7)
        spinner7.adapter = adapter7

        val createTask = findViewById<Button>(R.id.createTask)

        createTask.setOnClickListener {
            var text = ""
            val duration = Toast.LENGTH_SHORT


            if (spinner7.selectedItem.toString() == "Select Category..." || chipgroup1.isEmpty() ||
                chipgroup2.isEmpty() || startDate.text.toString() == "" || finishDate.text.toString() == ""
                || taskTitle.text.toString() == "" || delegated.text.toString() == "") {
                text = "Please complete all forms!"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            } else {

                MySingleton.getInstance(this).createTask(
                    taskTitle.text.toString(),
                    priority.checkedRadioButtonId.toString(),
                    startDate.text.toString(),
                    finishDate.text.toString(),
                    spinner7.selectedItem.toString(),
                    delegated.text.toString(),
                    comment.text.toString()
                )
                text = "Task created!"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
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
            startDate.text = "$savedYear-$savedMonth-$savedDay"
        } else {
            finishDate.text = "$savedYear-$savedMonth-$savedDay"
        }
    }
}
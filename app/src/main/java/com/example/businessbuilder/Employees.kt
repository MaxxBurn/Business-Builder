package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Employees : AppCompatActivity() {
    lateinit var button1: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val text1 = findViewById<EditText>(R.id.user_name)
        val text2 = findViewById<EditText>(R.id.user_lastname)
        val text3 = findViewById<EditText>(R.id.user_email)
        val text4 = findViewById<EditText>(R.id.user_position)
        val text5 = findViewById<EditText>(R.id.passwordbar)

        val userRadioButton = findViewById<RadioButton>(R.id.status_user)
        val superuserRadioButton = findViewById<RadioButton>(R.id.status_superuser)
        val administratorRadioButton = findViewById<RadioButton>(R.id.status_administrator)


        registerButton.setOnClickListener {
            var status: String = ""
            if(userRadioButton.isChecked){
                status = "User"
            }
            else if(superuserRadioButton.isChecked){
                status = "Super Users"
            }
            else if (administratorRadioButton.isChecked){
                status = "Administrator"
            }

            if (text5.text.toString() == "" || text4.text.toString() == "" || text3.text.toString() == ""
                || text2.text.toString() == "" || text1.text.toString() == "") {
                val duration = Toast.LENGTH_SHORT
                val text = "Fill your details"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            } else {
                MySingleton.getInstance(this).insertRegister(
                    text1.text.toString(), text2.text.toString(), text3.text.toString(),
                    text4.text.toString(), text5.text.toString(), status)
                val duration = Toast.LENGTH_SHORT
                val text: String = "Registration Complete!"
                val toast = Toast.makeText(this, text,duration)
                toast.show()
            }
        }
    }
}
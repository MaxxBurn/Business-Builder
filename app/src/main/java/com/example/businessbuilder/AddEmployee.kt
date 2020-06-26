package com.example.businessbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddEmployee : AppCompatActivity() {
    lateinit var toggleButton1: FloatingActionButton
    lateinit var toggleButton2: FloatingActionButton
    lateinit var toggleButton3: FloatingActionButton
    lateinit var toggleButton4: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        toggleButton1 = findViewById(R.id.toggleButton1)
        val firstlayout: ConstraintLayout = findViewById(R.id.firstToggleTexts)
        var isToggled1: Boolean = false

        toggleButton2 = findViewById(R.id.toggleButton2)
        val secondLayout: ConstraintLayout = findViewById(R.id.secondToggleText)
        var isToggled2: Boolean = false
        toggleButton3 = findViewById(R.id.toggleButton3)
        val thirdLayout: ConstraintLayout = findViewById(R.id.thirdToggleText)
        var isToggled3: Boolean = false

        toggleButton4 = findViewById(R.id.toggleButton4)
        val forthLayout: ConstraintLayout = findViewById(R.id.forthToggleText)
        var isToggled4: Boolean = false



        toggleButton1.setOnClickListener {
            if(!isToggled1){
                firstlayout.visibility = View.VISIBLE
                isToggled1 = true
            }
            else{
                firstlayout.visibility = View.GONE
                isToggled1 = false
            }
        }
        toggleButton2.setOnClickListener {
            if(!isToggled2){
                secondLayout.visibility = View.VISIBLE
                isToggled2 = true
            }
            else{
                secondLayout.visibility = View.GONE
                isToggled2 = false
            }
        }
        toggleButton3.setOnClickListener {
            if(!isToggled3){
                thirdLayout.visibility = View.VISIBLE
                isToggled3 = true
            }
            else{
                thirdLayout.visibility = View.GONE
                isToggled3 = false
            }
        }
        toggleButton4.setOnClickListener {
            if(!isToggled4){
                forthLayout.visibility = View.VISIBLE
                isToggled4 = true
            }
            else{
                forthLayout.visibility = View.GONE
                isToggled4 = false
            }
        }
    }
}
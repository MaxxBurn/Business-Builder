package com.example.businessbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class PlanningAction5 : AppCompatActivity() {

    //First Question
    lateinit var firstButton: Button
    lateinit var clickableText: TextView

    //Second Question
    lateinit var secondButton: Button

    //Third Question
    lateinit var thirdButton: Button

    //Forth Question
    lateinit var forthButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning_action5)

        //First Question
        firstButton = findViewById(R.id.FirstQuestionButton)
        val firstLayout: ConstraintLayout = findViewById(R.id.firstButtonLayout)
        var isOpen1: Boolean = false
        clickableText = findViewById(R.id.firstButtonText3)
        val secondLayout: ConstraintLayout = findViewById(R.id.textViewConstraint)
        var clickedText: Boolean = false

        //Second Question
        val secondButtonLayout: ConstraintLayout = findViewById(R.id.secondButtonLayout)
        secondButton = findViewById(R.id.secondButton)
        var clickedButton: Boolean = false

        //Third Question
        thirdButton = findViewById(R.id.thirdButton)
        val thirdButtonLayout:ConstraintLayout = findViewById(R.id.thirdLayoutButton)
        var clickedThirdButton: Boolean = false

        //Forth Question
        forthButton = findViewById(R.id.forthButton)
        val forthButtonLayout: ConstraintLayout = findViewById(R.id.forthButtonLayout)
        var clickedForthButton = false

        //First Question Click Listeners
        firstButton.setOnClickListener {
            if(!isOpen1){
                firstLayout.visibility = View.VISIBLE
                isOpen1 = true
            }
            else{
                firstLayout.visibility = View.GONE
                isOpen1 = false
            }
        }
        clickableText.setOnClickListener{
            if(!clickedText){
                secondLayout.visibility = View.VISIBLE
                clickedText = true
            }
            else{
                secondLayout.visibility = View.GONE
                clickedText = false
            }
        }
        secondButton.setOnClickListener {
            if(!clickedButton){
                secondButtonLayout.visibility = View.VISIBLE
                clickedButton = true
            }
            else{
                secondButtonLayout.visibility = View.GONE
                clickedButton = false
            }
        }
        thirdButton.setOnClickListener {
            if(!clickedThirdButton){
                thirdButtonLayout.visibility = View.VISIBLE
                clickedThirdButton = true
            }
            else{
                thirdButtonLayout.visibility = View.GONE
                clickedThirdButton = false
            }
        }
        forthButton.setOnClickListener {
            if(!clickedForthButton){
                forthButtonLayout.visibility = View.VISIBLE
                clickedForthButton = true
            }
            else{
                forthButtonLayout.visibility = View.GONE
                clickedForthButton = false
            }
        }


    }
}
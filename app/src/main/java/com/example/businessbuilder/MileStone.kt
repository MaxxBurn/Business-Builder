package com.example.businessbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MileStone : AppCompatActivity() {
    lateinit var dropButton : FloatingActionButton
    lateinit var cl1 : ConstraintLayout
    lateinit var cl2 : ConstraintLayout
    lateinit var cl3 : ConstraintLayout
    lateinit var cl4 : ConstraintLayout
    lateinit var cl5 : ConstraintLayout
    lateinit var cl6 : ConstraintLayout
    lateinit var cl7 : ConstraintLayout
    lateinit var cl8 : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mile_stone)

        var isItDropped: Boolean = false
        dropButton = findViewById(R.id.dropButton)
        cl1 = findViewById(R.id.cl1)
        cl2 = findViewById(R.id.cl2)
        cl3 = findViewById(R.id.cl3)
        cl4 = findViewById(R.id.cl4)
        cl5 = findViewById(R.id.cl5)
        cl6 = findViewById(R.id.cl6)
        cl7 = findViewById(R.id.cl7)
        cl8 = findViewById(R.id.cl8)


        dropButton.setOnClickListener {
            if(!isItDropped){
                cl1.visibility = View.VISIBLE
                cl2.visibility = View.VISIBLE
                cl3.visibility = View.VISIBLE
                cl4.visibility = View.VISIBLE
                cl5.visibility = View.VISIBLE
                cl6.visibility = View.VISIBLE
                cl7.visibility = View.VISIBLE
                cl8.visibility = View.VISIBLE
                isItDropped = true
            }
            else{
                cl1.visibility = View.GONE
                cl2.visibility = View.GONE
                cl3.visibility = View.GONE
                cl4.visibility = View.GONE
                cl5.visibility = View.GONE
                cl6.visibility = View.GONE
                cl7.visibility = View.GONE
                cl8.visibility = View.GONE

                isItDropped = false
            }
        }
    }
}
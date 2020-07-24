package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.logIn)
        val button2 = findViewById<Button>(R.id.companyProfile)
        val button3 = findViewById<ImageButton>(R.id.dailyTransactions)
        val button4 = findViewById<ImageButton>(R.id.planningAction)
        val button5 = findViewById<ImageButton>(R.id.keyPeople)
        val button6 = findViewById<ImageButton>(R.id.operationalGuide)
        val button7 = findViewById<ImageButton>(R.id.resources)
        val button8 = findViewById<ImageButton>(R.id.executiveRoutine)

        button7.visibility = INVISIBLE


        button1.setOnClickListener{
            val logInMenu = Intent(this, LogInActivity::class.java)
            startActivity(logInMenu)
        }

        button3.setOnClickListener{
            val firstMenu = Intent(this,DailyTransaction::class.java)
            startActivity(firstMenu)
        }
        button4.setOnClickListener{
            val secondMenu = Intent(this,PlanningAction::class.java)
            startActivity(secondMenu)
        }
        button5.setOnClickListener {
            val thirdMenu = Intent(this,KeyPeople::class.java)
            startActivity(thirdMenu)
        }
        button6.setOnClickListener {
            val forthMenu = Intent(this,OperationalGuides::class.java)
            startActivity(forthMenu)
        }
        button7.setOnClickListener{
            val fifthMenu = Intent(this, Resources::class.java)
            startActivity(fifthMenu)
        }
        button8.setOnClickListener {
            val sixthMenu = Intent(this, ExecutiveRoutine::class.java)
            startActivity(sixthMenu)
        }


    }
}

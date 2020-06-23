package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    //First Buttons in the main menu
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: ImageButton
    lateinit var button4: ImageButton
    lateinit var button5: ImageButton
    lateinit var button6: ImageButton
    lateinit var button7: ImageButton
    lateinit var button8: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Buttons
        button1 = findViewById(R.id.logIn)
        button2 = findViewById(R.id.companyProfile)
        button3 = findViewById<ImageButton>(R.id.dailyTransactions)
        button4 = findViewById<ImageButton>(R.id.planningAction)
        button5 = findViewById<ImageButton>(R.id.keyPeople)
        button6 = findViewById<ImageButton>(R.id.operationalGuide)
        button7 = findViewById<ImageButton>(R.id.resources)
        button8 = findViewById<ImageButton>(R.id.executiveRoutine)

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

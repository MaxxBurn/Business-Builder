package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class AdministratorMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrator_menu)


        val button2 = findViewById<Button>(R.id.companyProfile1)
        val button3 = findViewById<ImageButton>(R.id.dailyTransactions)
        val button4 = findViewById<ImageButton>(R.id.planningAction)
        val button5 = findViewById<ImageButton>(R.id.tasksMenu)
        val button6 = findViewById<ImageButton>(R.id.operationalGuide)
        val button7 = findViewById<ImageButton>(R.id.resources)
        val button8 = findViewById<ImageButton>(R.id.keyPeople)
        val logInButton = findViewById<Button>(R.id.logIn)
        val logoutButton = findViewById<Button>(R.id.companyProfile)

        logoutButton.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        logInButton.setText("Welcome ${SESSION_NAME}")

            button3.setOnClickListener {
                val firstMenu = Intent(this, DailyTransaction::class.java)
                startActivity(firstMenu)
            }
            button4.setOnClickListener {
                val secondMenu = Intent(this, PlanningAction::class.java)
                startActivity(secondMenu)
            }
            button5.setOnClickListener {
                val thirdMenu = Intent(this, Tasks::class.java)
                startActivity(thirdMenu)
            }
            button6.setOnClickListener {
                val forthMenu = Intent(this, OperationalGuides::class.java)
                startActivity(forthMenu)
            }
            button7.setOnClickListener {
                val fifthMenu = Intent(this, BudgetMenu::class.java)
                startActivity(fifthMenu)
            }
            button8.setOnClickListener {
                val sixthMenu = Intent(this, KeyPeople::class.java)
                startActivity(sixthMenu)
            }
        }
    override fun onBackPressed() {
        this.moveTaskToBack(true);
    }
}
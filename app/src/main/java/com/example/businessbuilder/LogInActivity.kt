package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar

data class LogInStuff(val email:String, val password:String){
    override fun toString(): String {
        return "${this.email}\n${this.password}"
    }
}

class LogInActivity : AppCompatActivity() {

    lateinit var logButton: Button
    lateinit var email: EditText
    lateinit var loading : ProgressBar  

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        logButton = findViewById(R.id.login)
        email = findViewById(R.id.userName)
        loading = findViewById(R.id.loading)
        val passwordLog = findViewById<EditText>(R.id.passwordbar)

        logButton.setOnClickListener {
            MySingleton.getInstance(this).logIn(this, email.text.toString(), passwordLog.text.toString())
            if (SESSION_STATUS == "User" || SESSION_STATUS == "Super Users"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else if(SESSION_STATUS == "Administrator"){
                val intent = Intent(this, AdministratorMenu::class.java)
                startActivity(intent)
            }

        }
    }
    //Used to create another level of abstraction

    /* private fun requestAbstractor(){
        MySingleton.getInstance(this).buttonRequest(URL, logButton,loading)
    }*/

}
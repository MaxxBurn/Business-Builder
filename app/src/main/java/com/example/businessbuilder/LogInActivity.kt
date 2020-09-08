package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar


class LogInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        val logButton = findViewById<Button>(R.id.login)
        val email = findViewById<EditText>(R.id.userName)
        val passwordLog = findViewById<EditText>(R.id.passwordbar)

        SESSION_STATUS = ""
        SESSION_ID = ""
        SESSION_NAME = ""
        logButton.setOnClickListener {
            MySingleton.getInstance(this).logIn(this, email.text.toString(), passwordLog.text.toString())
        }
    }
    override fun onBackPressed() {
        this.moveTaskToBack(true);
    }
}
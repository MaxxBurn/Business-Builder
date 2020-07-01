package com.example.businessbuilder

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class LogInActivity : AppCompatActivity() {
    //URL's
    val URL = "https://www.google.com"
    lateinit var logButton: Button
    lateinit var username: TextView
    lateinit var loading : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        logButton = findViewById(R.id.LOGIN)
        username = findViewById(R.id.username)
        loading = findViewById(R.id.loading)
        logButton.setOnClickListener {
            requestAbstractor()
        }
    }
    //Used to create another level of abstraction
    private fun requestAbstractor(){
        MySingleton.getInstance(this).buttonRequest(URL, logButton,loading)
    }
}
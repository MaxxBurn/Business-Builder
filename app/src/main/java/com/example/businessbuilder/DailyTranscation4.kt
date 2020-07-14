package com.example.businessbuilder

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_daily_transcation4.*
import org.json.JSONArray
import org.json.JSONObject

class DailyTranscation4 : AppCompatActivity() {

    lateinit var addCashBook: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transcation4)

        addCashBook = findViewById(R.id.cashBookAddButton)
        val button = findViewById<Button>(R.id.apitest)
        val insertName = findViewById<EditText>(R.id.insertName)



        addCashBook.setOnClickListener {
            val intent = Intent(this,Cashbook::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            insert()
        }
    }

}
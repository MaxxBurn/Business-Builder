package com.example.businessbuilder

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    lateinit var testText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transcation4)

        addCashBook = findViewById(R.id.cashBookAddButton)
        testText = findViewById(R.id.testAPI)
        val json = JSONObject()
        json.put("name","test name")
        json.put("age","25")

        testJson.setOnClickListener {
            MySingleton.getInstance(this).updateDatabase("https://api.github.com/search/users?q=eyehunt",json)
        }
        addCashBook.setOnClickListener {
            val intent = Intent(this,Cashbook::class.java)
            startActivity(intent)
        }
    }

}
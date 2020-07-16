package com.example.businessbuilder

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_daily_transcation4.*
import org.json.JSONArray
import org.json.JSONObject


data class Users(var name: String, var password: String, var email: String) {
    override fun toString(): String {
        return "${this.name}\n${this.password}\n${this.email}"
    }
}

class DailyTranscation4 : AppCompatActivity() {

    lateinit var addCashBook: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transcation4)

        val list: ListView = findViewById(R.id.listtest)


        addCashBook = findViewById(R.id.cashBookAddButton)
        val button = findViewById<Button>(R.id.apitest)
        val getJson = findViewById<Button>(R.id.getJson)


        addCashBook.setOnClickListener {
            val intent = Intent(this,Cashbook::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            MySingleton.getInstance(this).insertData(insertData)
        }
        getJson.setOnClickListener {
            MySingleton.getInstance(this).getData(getData, list,this)
        }
    }

}
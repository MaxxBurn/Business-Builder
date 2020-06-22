package com.example.businessbuilder

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DailyTransaction6 : AppCompatActivity() {
    lateinit var saveButtonAsset : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_transaction6)

        saveButtonAsset = findViewById(R.id.addAssetButton)
        saveButtonAsset.setOnClickListener {
            val intent1 = Intent(this,AssetRegister::class.java)
            startActivity(intent1)
        }

    }

}
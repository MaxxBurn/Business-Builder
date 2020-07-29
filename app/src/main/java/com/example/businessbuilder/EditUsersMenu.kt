package com.example.businessbuilder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

data class UserDetails(val name: String, val lastname: String, val status: String, val email: String ){
    override fun toString(): String {
        return "Name: ${this.name}\nLastname: ${this.lastname}\nStatus: ${this.status}\nEmail: ${this.email}"
    }
}

class EditUsersMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_users_menu)

        val given = intent.getStringExtra("autoComplete")
    }
}
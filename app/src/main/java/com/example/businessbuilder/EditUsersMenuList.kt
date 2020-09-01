package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class EditUsersMenuList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_users)

        val yeet = mutableListOf<CharSequence>()
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(applicationContext, android.R.layout.simple_selectable_list_item, yeet)
        val list: ListView = findViewById(R.id.userList)

        MySingleton.getInstance(this).getUserDetails(list,this, yeet, adapter)

        list.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            val intent = Intent(this, EditUsersMenu::class.java)
//            intent.putExtra("autoComplete", element)
            startActivity(intent)
        }



    }
}
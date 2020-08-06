package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.*
import androidx.core.text.HtmlCompat
import org.w3c.dom.Text

data class UserDetails(val name: String, val lastname: String, val status: String, val email: String, val context:Context ){
     fun toString1(): CharSequence {
         val text = TextView(context)
         val html: String = "<b>" + name +" " + lastname + " </b>- " + email + " - " + status
         text.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
         return text.text
    }
}
class EditUsersMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_users_menu)

        val given = intent.getCharSequenceExtra("autoComplete").toString()
        var nameBefore: String = ""
        var lnameBefore: String = ""
        var keepinMind : Int = 0
        var count = 0

        for(i in keepinMind until given.length){
            if(given[i] == ' '){
                count ++
                keepinMind = i + 1
                continue
            }
            if(count == 0){
                nameBefore += given[i]
            }
            else if(count == 1){
                lnameBefore += given[i]
            }
        }

        val updateButton = findViewById<Button>(R.id.updateUsersButton)
        val nameText = findViewById<EditText>(R.id.textxt1)
        val lastnameText = findViewById<EditText>(R.id.textxt2)
        val emailText = findViewById<EditText>(R.id.textxt3)
        val positionText = findViewById<EditText>(R.id.textxt4)

        val userRadioButton = findViewById<RadioButton>(R.id.radioButton3)
        val superuserRadioButton = findViewById<RadioButton>(   R.id.radioButton4)
        val administratorRadioButton = findViewById<RadioButton>(R.id.radioButton5)
        val activeRadioButton = findViewById<RadioButton>(R.id.radioButton6)
        val inactiveRadioButton = findViewById<RadioButton>(R.id.radioButton7)

        updateButton.setOnClickListener {
            var userstatus: String = ""
            var status: String = ""

            if (userRadioButton.isChecked) {
                userstatus = "User"
            } else if (superuserRadioButton.isChecked) {
                userstatus = "Super Users"
            } else if(administratorRadioButton.isChecked) {
                userstatus = "Administrator"
            }
            if(activeRadioButton.isChecked){
                status = "Active"
            }
            else if(inactiveRadioButton.isChecked){
                status = "Inactive"
            }
            if (nameText.text.toString() == "" || lastnameText.text.toString() == "" || emailText.text.toString() == ""
                || positionText.text.toString() == "") {
                val duration = Toast.LENGTH_SHORT
                val text = "Fill user details."
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            } else {
                MySingleton.getInstance(this).updateUsers(this, nameText.text.toString(), lastnameText.text.toString(),
                emailText.text.toString(), positionText.text.toString(), userstatus, status, nameBefore, lnameBefore)

                val intent = Intent(this, KeyPeople::class.java)
                startActivity(intent)
            }
        }
    }
}
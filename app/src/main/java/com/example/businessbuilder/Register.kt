package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_register.*

data class RegistrationInfo(val username: String, val name: String, val lastname: String, val email: String, val position: String,
                            val password: String, val status: String){
    override fun toString(): String {
        return "${this.username}\n${this.name}\n${this.lastname}\n${this.email}\n${this.position}"
    }

}
class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val text1 = findViewById<EditText>(R.id.user_name)
        val text2 = findViewById<EditText>(R.id.user_lastname)
        val text3 = findViewById<EditText>(R.id.user_email)
        val text4 = findViewById<EditText>(R.id.user_position)
        val text5 = findViewById<EditText>(R.id.passwordbar)

        val userRadioButton = findViewById<RadioButton>(R.id.status_user)
        val superuserRadioButton = findViewById<RadioButton>(R.id.status_superuser)
        val administratorRadioButton = findViewById<RadioButton>(R.id.status_administrator)



        registerButton.setOnClickListener {
            var status: String = ""
            if(userRadioButton.isChecked){
                status = "User"
            }
            else if(superuserRadioButton.isChecked){
                status = "SuperUser"
            }
            else{
                status = "Admin"
            }

            if (text5.text.toString() == "" || text4.text.toString() == "" || text3.text.toString() == ""
                || text2.text.toString() == "" || text1.text.toString() == "") {
                val duration = Toast.LENGTH_SHORT
                val text = "Fill your details"
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            } else {
                MySingleton.getInstance(this).insertRegister(
                    text1.text.toString(), text2.text.toString(), text3.text.toString(),
                    text4.text.toString(), text5.text.toString(), status)
                val duration = Toast.LENGTH_SHORT
                val text: String = "Registration Complete!"
                val toast = Toast.makeText(this, text,duration)
                toast.show()
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
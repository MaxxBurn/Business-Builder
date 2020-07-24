package com.example.businessbuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

class Cashbook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cashbook)

        val given = intent.getStringExtra("autoComplete")
        val text: EditText = findViewById(R.id.date)
        val text2: EditText = findViewById(R.id.details)
        val text3: EditText = findViewById(R.id.references)
        val text4: EditText = findViewById(R.id.department)
        val text5: EditText = findViewById(R.id.cashIn)
        val text6: EditText = findViewById(R.id.cashOut)
        val text7: EditText = findViewById(R.id.balance)


        if(given != null) {
            autoCompleteFields(text, text2, text3, text4, text5, text6, text7, given)
        }
        val cashBookButton: ImageButton = findViewById(R.id.addCashBook)
        cashBookButton.setOnClickListener {
            MySingleton.getInstance(this).insertData14(text.text.toString(), text2.text.toString(), text3.text.toString(),
                text4.text.toString(), text5.text.toString(), text6.text.toString(), text7.text.toString())
            val intent = Intent(this, DailyTranscation4::class.java)
            startActivity(intent)
        }
    }
    fun autoCompleteFields(text: EditText,text2:EditText, text3:EditText, text4:EditText,text5:EditText,text6:EditText,
                           text7:EditText,given: String){
        var name: String = ""
        var keepInMind : Int = 0
        var count : Int = 0
        for(i in given.indices){
            if(given[i] == '\n' && count == 0){
                for(j in keepInMind until i){
                    name += given[j]
                }
                keepInMind = i + 1
                text.setText(name)
                count += 1
            }
            else if(given[i] == '\n' && count == 1){
                name = ""
                for(j in keepInMind until i){
                    name += given[j]
                }
                keepInMind = i + 1
                text2.setText(name)
                count += 1
            }
            else if(given[i] == '\n' && count == 2){
                name = ""
                for(j in keepInMind until i){
                    name += given[j]
                }
                keepInMind = i + 1
                text3.setText(name)
                count += 1
            }
            else if(given[i] == '\n' && count == 3){
                name = ""
                for(j in keepInMind until i){
                    name += given[j]
                }
                keepInMind = i + 1
                text4.setText(name)
                count += 1
            }
            else if(given[i] == '\n' && count == 4){
                name = ""
                for(j in keepInMind until i){
                    name += given[j]
                }
                keepInMind = i + 1
                text5.setText(name)
                count += 1
            }
            else if(given[i] == '\n' && count == 5){
                name = ""
                for(j in keepInMind until i){
                    name += given[j]
                }
                keepInMind = i + 1
                text6.setText(name)
                count += 1
            }
            else if(given[i] == ' ' && count == 6){
                name = ""
                for(j in keepInMind until i){
                    name += given[j]
                }
                keepInMind = i + 1
                text7.setText(name)
                count += 1
            }
        }
    }
}

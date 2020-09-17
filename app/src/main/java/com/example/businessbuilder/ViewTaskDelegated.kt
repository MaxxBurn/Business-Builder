package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.HtmlCompat

class DelegatedAdapter(context: Context, list: ArrayList<DelegatedClass>) :
    ArrayAdapter<DelegatedClass>(context, 0, list as List<DelegatedClass?>) {
    private var mContext: Context
    private var moviesList: List<DelegatedClass> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.listofdelegated, parent, false)

        val currentMovie = moviesList[position]
        val name = listItem?.findViewById(R.id.textView_name12) as TextView
        name.text = currentMovie.getName()

        var letters = ""
        var count = 0
        var yeet = false

        for(i in currentMovie.getName()){
            if(yeet == true){
                letters += i
                yeet = false
            }
            if(count == 0){
                letters += i
                count++
            }
            if(i == ' '){
                yeet = true
            }
        }

        val status = listItem.findViewById(R.id.textView_release12) as TextView
        status.text = currentMovie.getStatus()
        val circular = listItem.findViewById(R.id.imageView_poster1) as TextView
        circular.text = letters
        val backProgress = listItem.findViewById(R.id.button111) as Button
        backProgress.text = "Back To Progress"
        val document = listItem.findViewById(R.id.button11) as Button
        document.text = "Document"
        return listItem
    }
    init {
        moviesList = list
        mContext = context;
    }
}
class DelegatedClass(
    private var id_user_task: String,
    private var status: String,
    private var name: String,
    private var document: String,
    private var percentage: String
) {
    fun getTaskUser():String{
        return id_user_task
    }
    fun getStatus():String{
        return status
    }
    fun getName(): String{
        return name
    }
    fun getDocument(): String{
        return document
    }
    fun getPercentage(): String{
        return percentage
    }
}

class ViewTaskDelegated : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task_delegated)

        val givenId = intent.getStringExtra("id")
        val givenTitle = intent.getStringExtra("title")
        val givenStatus = intent.getStringExtra("status")
        val givenCategory = intent.getStringExtra("category")

        val statusText = findViewById<TextView>(R.id.statusOfTask1)

        var stringYeet = "<b>Status:</b> $givenStatus"
        statusText.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))

        val titleText = findViewById<TextView>(R.id.titleOfTask1)
        titleText.text = "Title: $givenTitle"
        val categoryText = findViewById<TextView>(R.id.categoryOfTask1)
        categoryText.text = "Category: $givenCategory"
        val text1 = findViewById<TextView>(R.id.commentTask1)
        val text2= findViewById<TextView>(R.id.priorityTask1)
        val text3 = findViewById<TextView>(R.id.startFinishTask1)
        val text4 = findViewById<TextView>(R.id.createdTask1)
        val text5 = findViewById<TextView>(R.id.delegatedTask1)
        val button = findViewById<Button>(R.id.button260)

        button.setOnClickListener {
            MySingleton.getInstance(this).submitDelegatedTask(givenId)
            val intent = Intent(this, Tasks::class.java)
            startActivity(intent)
        }

        val userlist = findViewById<ListView>(R.id.yeetlist12)
        val tasksList : ArrayList<UsersAndBusinessInTask> = ArrayList()
        val tasksList1 : ArrayList<DelegatedClass> = ArrayList()
        val userlist1 = findViewById<ListView>(R.id.yeetlist1222)

        MySingleton.getInstance(this).getUsersAndBusiness(this, userlist, tasksList, givenId, text1, text2, text3, text4, text5)

        MySingleton.getInstance(this).getDelegated2ndUsers(userlist1, this, tasksList1, givenId)


    }
}
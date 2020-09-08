package com.example.businessbuilder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class UsersAndBusinessAdapter(context: Context, list: ArrayList<UsersAndBusinessInTask>) :
    ArrayAdapter<UsersAndBusinessInTask>(context, 0, list as List<UsersAndBusinessInTask?>) {
    private var mContext: Context
    private var moviesList: List<UsersAndBusinessInTask> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.users_business, parent, false)
        val currentMovie = moviesList[position]
        val name = listItem?.findViewById(R.id.textView22223) as TextView
        name.text = currentMovie.getTaskUser()
        val release = listItem.findViewById(R.id.textView2222) as TextView
        release.text = currentMovie.getTaskBusiness()

        return listItem
    }
    init {
        moviesList = list
        mContext = context;
    }
}
class UsersAndBusinessInTask(
    private var taskUsers: String,
    private var taskBusiness: String
) {
    fun getTaskUser():String{
        return taskUsers
    }
    fun getTaskBusiness():String{
        return taskBusiness
    }
}

class ViewTasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)
        this.title = "Task Details"

        val givenId = intent.getStringExtra("id")
        val givenTitle = intent.getStringExtra("title")
        val givenStatus = intent.getStringExtra("status")
        val givenCategory = intent.getStringExtra("category")

        val statusText = findViewById<TextView>(R.id.statusOfTask)
        statusText.text = "Status: $givenStatus"
        val titleText = findViewById<TextView>(R.id.titleOfTask)
        titleText.text = "Title: $givenTitle"
        val categoryText = findViewById<TextView>(R.id.categoryOfTask)
        categoryText.text = "Category: $givenCategory"

        val text1 = findViewById<TextView>(R.id.commentTask)
        val text2= findViewById<TextView>(R.id.priorityTask)
        val text3 = findViewById<TextView>(R.id.startFinishTask)
        val text4 = findViewById<TextView>(R.id.delgatedTask)
        val text5 = findViewById<TextView>(R.id.statusOfTask)


        val userlist = findViewById<ListView>(R.id.yeetlist)
        val tasksList : ArrayList<UsersAndBusinessInTask> = ArrayList()

        MySingleton.getInstance(this).getUsersAndBusiness(this, userlist, tasksList, givenId, text1, text2, text3, text4)


    }
}
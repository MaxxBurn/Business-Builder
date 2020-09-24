package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.*

class TaskAdapter(context: Context, list: ArrayList<TasksInBusiness>) :
    ArrayAdapter<TasksInBusiness>(context, 0, list as List<TasksInBusiness?>) {
    private var mContext: Context
    private var moviesList: List<TasksInBusiness> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.tasks_menu_table_all, parent, false)
        val currentMovie = moviesList[position]
        val name = listItem?.findViewById(R.id.taskTitle1) as TextView
        name.text = currentMovie.getTaskTitle()
        val release = listItem.findViewById(R.id.taskStatus) as TextView
        release.text = currentMovie.getTaskStatus1()
        val type = listItem.findViewById(R.id.taskType)  as TextView
        type.text = currentMovie.getTaskType()
        val date = listItem.findViewById(R.id.dateCreated1) as TextView
        date.text = currentMovie.getDateCreated()
        return listItem
    }

    init {
        moviesList = list
        mContext = context;
    }
}

class TasksInBusiness(
    private var taskId: String,
    private var taskTitle: String, var taskStatus: String,
    private var taskType: String, private var dateCreated: String
) {
    fun getTaskId(): String {
        return taskId
    }
    fun getTaskTitle(): String {
        return taskTitle
    }
    fun getTaskStatus1(): String{
        return taskStatus
    }
    fun getTaskType():String{
        return taskType
    }
    fun getDateCreated():String{
        return dateCreated
    }
}

class TaskMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_menu)

        val nameBusiness = intent.getStringExtra("nameBusiness")
        this.title = "$nameBusiness Tasks"


        val useless = intent.getStringExtra("autoComplete")
        val given = intent.getStringExtra("nameForOtherTaskMenu")

        val listView = findViewById<ListView>(R.id.listView20)
        val tasksList : ArrayList<TasksInBusiness> = ArrayList()
        val button: ImageButton = findViewById(R.id.floatingActionButton)
        val adapter1 = TaskAdapter(this, tasksList)

        MySingleton.getInstance(this).getTasksMenu(given, listView, this, tasksList, "0")

        button.setOnClickListener {
            tasksList.clear()
            MySingleton.getInstance(this).getTasksMenu(given, listView, this, tasksList, "1")

            button.visibility = GONE
        }
        listView.setOnItemClickListener { parent, view, position, id ->
            val element = adapter1.getItem(position)
            val intent = Intent(this, ViewTasks::class.java)
            intent.putExtra("whichMenu", "TaskMenu")
            intent.putExtra("id",element?.getTaskId())
            intent.putExtra("status", element?.getTaskStatus1())
            intent.putExtra("title", element?.getTaskTitle())
            intent.putExtra("category", element?.getTaskType())
            startActivity(intent)
        }
    }
}
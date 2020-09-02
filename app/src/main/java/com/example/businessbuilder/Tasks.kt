package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class TaskMenuList(
    private var mImageDrawable: Bitmap,
    private var name: String, var number: String,
    private var username: String, private var dailyPending: String, private var dailyFinished: String, private var dailyProgress:String, private var dailyReject: String,
    private var requestedPending: String, private var requestedFinished: String, private var requestedProgress: String, private var requestedReject: String,
    private var requirePending: String, private var requireFinished: String, private var requireProgress: String, private var requireReject: String
) {

    fun getmImageDrawable(): Bitmap {
        return mImageDrawable
    }
    fun getmName(): String {
        return name
    }
    fun getNumber1(): String {
        return number
    }


    fun getUserName(): String{
        return username
    }
    fun getDailyPending():String{
        return dailyPending
    }
    fun getDailyFinished():String{
        return dailyFinished
    }
    fun getDailyProgress():String{
        return dailyProgress
    }
    fun getDailyReject():String{
        return dailyReject
    }

    fun getRequestedPending():String{
        return requestedPending
    }
    fun getRequestedFinished():String{
        return requestedFinished
    }
    fun getRequestedProgress():String{
        return requestedProgress
    }
    fun getRequestedReject():String{
        return requestedReject
    }

    fun getRequirePending(): String{
        return requirePending
    }
    fun getRequireFinished(): String{
        return requireFinished
    }
    fun getRequireProgress(): String{
        return requireProgress
    }
    fun getRequireReject(): String{
        return requireReject
    }
}


class TaskMenuAdapter(context: Context, list: ArrayList<TaskMenuList>) :
    ArrayAdapter<TaskMenuList?>(context, 0, list as List<TaskMenuList?>) {
    private var mContext: Context
    private var taskList: List<TaskMenuList> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.tasks_daily_monthly, parent, false)
        val currentTaskList = taskList[position]

        val image: ImageView = listItem?.findViewById(R.id.imageView_poster1) as ImageView
        image.setImageBitmap(currentTaskList.getmImageDrawable())
        val name = listItem.findViewById(R.id.textView_name1) as TextView
        name.text = currentTaskList.getmName()
        val number = listItem.findViewById(R.id.textView_release1) as TextView
        number.text = currentTaskList.getNumber1()
        val dailyPending = listItem.findViewById(R.id.dailyPending) as TextView
        dailyPending.text = currentTaskList.getDailyPending()
        val dailyFinished = listItem.findViewById(R.id.dailyFinished) as TextView
        dailyFinished.text = currentTaskList.getDailyFinished()
        val dailyProgress = listItem.findViewById(R.id.dailyProgress) as TextView
        dailyProgress.text = currentTaskList.getDailyProgress()
        val dailyReject = listItem.findViewById(R.id.dailyRejected) as TextView
        dailyReject.text = currentTaskList.getDailyReject()

        val requestPending = listItem.findViewById(R.id.requestedPending) as TextView
        requestPending.text = currentTaskList.getRequestedPending()
        val requestFinished= listItem.findViewById(R.id.requestedFinished) as TextView
        requestFinished.text = currentTaskList.getRequestedFinished()
        val requestProgress = listItem.findViewById(R.id.requestedProgress) as TextView
        requestProgress.text = currentTaskList.getRequestedProgress()
        val requestReject = listItem.findViewById(R.id.requestedReject) as TextView
        requestReject.text = currentTaskList.getRequestedReject()

        val requirePending = listItem.findViewById(R.id.requirePending) as TextView
        requirePending.text = currentTaskList.getDailyPending()
        val requireFinished = listItem.findViewById(R.id.requireFinished) as TextView
        requireFinished.text = currentTaskList.getDailyFinished()
        val requireProgress= listItem.findViewById(R.id.requireProgress) as TextView
        requireProgress.text = currentTaskList.getDailyProgress()
        val requireReject= listItem.findViewById(R.id.requireReject) as TextView
        requireReject.text = currentTaskList.getDailyReject()

        return listItem
    }

    init {
        taskList = list
        mContext = context;
    }
}



class Tasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val addTasks = findViewById<ImageButton>(R.id.addTask)
        val taskList: ArrayList<TaskMenuList> = ArrayList()
        val adapter1 = TaskMenuAdapter(this, taskList)
        val list : ListView = findViewById(R.id.listView3)


        MySingleton.getInstance(this).getMoop(this, list, taskList )

        list.setOnItemClickListener { parent, view, position, id ->
            val element = adapter1.getItem(position)
            val intent = Intent(this, TaskMenu::class.java)
            intent.putExtra("autoComplete",element?.getUserName())
            intent.putExtra("nameBusiness", element?.getmName())
            startActivity(intent)
        }

        addTasks.setOnClickListener {
            val intent = Intent(this, AddTasksMenu::class.java)
            startActivity(intent)
        }
    }
}
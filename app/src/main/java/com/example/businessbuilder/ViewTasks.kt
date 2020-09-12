package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_edit_budget_menu.*
import kotlinx.android.synthetic.main.activity_view_tasks.*

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

        val whichMenu = intent.getStringExtra("whichMenu")
        val givenId = intent.getStringExtra("id")
        val givenTitle = intent.getStringExtra("title")
        val givenStatus = intent.getStringExtra("status")
        val givenCategory = intent.getStringExtra("category")

        val statusText = findViewById<TextView>(R.id.statusOfTask)

        var stringYeet = "<b>Status:</b> $givenStatus"
        statusText.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))

        val titleText = findViewById<TextView>(R.id.titleOfTask)
        titleText.text = "Title: $givenTitle"
        val categoryText = findViewById<TextView>(R.id.categoryOfTask)
        categoryText.text = "Category: $givenCategory"
        val text1 = findViewById<TextView>(R.id.commentTask)
        val text2= findViewById<TextView>(R.id.priorityTask)
        val text3 = findViewById<TextView>(R.id.startFinishTask)
        val text4 = findViewById<TextView>(R.id.createdTask)
        val text5 = findViewById<TextView>(R.id.delegatedTask)

        val completeradio = findViewById<RadioGroup>(R.id.radiogroup120)
        val rejectRadio = findViewById<RadioButton>(R.id.radioButton11)
        val acceptRadio = findViewById<RadioButton>(R.id.radioButton12)
        val submitButton = findViewById<Button>(R.id.button26)

        val firstneeded = findViewById<TableRow>(R.id.firstNeeded)
        val secondNeeded = findViewById<TableRow>(R.id.secondNeeded)
        val thirdNeeded = findViewById<TableRow>(R.id.thirdNeeded)
        val reasonNeeded = findViewById<EditText>(R.id.reasonNeeded)

        if(whichMenu == "MyTasks" && givenStatus == "Pending"){

            firstneeded.visibility= VISIBLE
            secondNeeded.visibility = VISIBLE
        }

        completeradio.setOnCheckedChangeListener { group, checkedId ->

            val radio: RadioButton = group.findViewById(checkedId)
            if (radio == rejectRadio) {
                thirdNeeded.visibility = VISIBLE
            }
            else{
                thirdNeeded.visibility = GONE
            }

        }

        submitButton.setOnClickListener {
            var yeet= ""
            if(rejectRadio.isChecked){
                yeet = "Rejected"
            }
            else if(acceptRadio.isChecked){
                yeet = "Progress"
            }
            if (yeet == ""){
                val text = "Reject or Accept the task!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
            else{
                if(thirdNeeded.visibility == GONE){
                    MySingleton.getInstance(this).submitTaskConfirmation(yeet, givenId,"")
                    firstneeded.visibility= GONE
                    secondNeeded.visibility = GONE
                    val intent = Intent(this, Tasks::class.java)
                    startActivity(intent)
                }
                else{
                    MySingleton.getInstance(this).submitTaskConfirmation(yeet, givenId, reasonNeeded.text.toString() )
                    firstneeded.visibility= GONE
                    secondNeeded.visibility = GONE
                    val intent = Intent(this, Tasks::class.java)
                    startActivity(intent)
                }
            }
        }
        val userlist = findViewById<ListView>(R.id.yeetlist)
        val tasksList : ArrayList<UsersAndBusinessInTask> = ArrayList()
        MySingleton.getInstance(this).getUsersAndBusiness(this, userlist, tasksList, givenId, text1, text2, text3, text4, text5)
    }
}
package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.LruCache
import android.view.View
import android.widget.*
import androidx.core.text.HtmlCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONArray
import org.json.JSONObject

private val dbConnectUrl: String = "https://albreezetours.com/android_register_login/Connection.php"
private val getHRUrl: String = "https://albreezetours.com/android_register_login/GetBusinessHR.php"
private val backToProgressUrl: String = "https://albreezetours.com/android_register_login/BackToProgress.php"
private val getDataUrl: String = "https://albreezetours.com/android_register_login/GetData.php"
private val updateDataUrl: String = "https://albreezetours.com/android_register_login/UpdateData.php"
private val getBusinessEmployeesUrl: String = "https://albreezetours.com/android_register_login/GetBusinessEmployees.php"
private val getMoopUrl: String = "https://albreezetours.com/android_register_login/GetMoop.php"
private val getDataUrl14: String = "https://albreezetours.com/android_register_login/GetData14.php"
private val getDelegatedUrl: String = "https://albreezetours.com/android_register_login/GetTaskDelegated.php"
private val getUserDetailsUrl: String = "https://albreezetours.com/android_register_login/GetUserDetails.php"
private val insertDataUrl14: String = "https://albreezetours.com/android_register_login/InsertData14.php"
private val createTaskUrl: String = "https://albreezetours.com/android_register_login/InsertTask.php"
private val insertRegister: String = "https://albreezetours.com/android_register_login/Register.php"
private val GetDelegated2Url: String = "https://albreezetours.com/android_register_login/GetDelegated2.php"
private val logInUrl: String = "https://albreezetours.com/android_register_login/Login.php"
private val GetTaskInfoUrl: String = "https://albreezetours.com/android_register_login/GetInfoForTask.php"
private val getUsersName : String = "https://albreezetours.com/android_register_login/GetUsersName.php"
private val getBusinessNames: String = "https://albreezetours.com/android_register_login/GetBusinessNames.php"
private val getBusinessNamesBudget: String = "https://albreezetours.com/android_register_login/GetBusinessNameBudget.php"
private val getReason: String = "https://albreezetours.com/android_register_login/GetReason.php"
private val getSpecificBusinessTaskUrl: String = "https://albreezetours.com/android_register_login/GetSpecificForBusinessTask.php"
private val getSpecificReason: String = "https://albreezetours.com/android_register_login/GetSpecificReason.php"
private val verifyPendingStatus: String = "https://albreezetours.com/android_register_login/VerifyPendingImage.php"
private val verifyPendingStatus1: String = "https://albreezetours.com/android_register_login/VerifyPendingImage1.php"
private val requestBudget: String = "https://albreezetours.com/android_register_login/RequestBudget.php"
private val getBusinessID: String = "https://albreezetours.com/android_register_login/GetBusinessId.php"
private val getUserID: String = "https://albreezetours.com/android_register_login/GetUserId.php"
private val getBudgetRequestListUrl: String = "https://albreezetours.com/android_register_login/GetBudgetList.php"
private val getBudgetRequestApprovedUrl:String = "https://albreezetours.com/android_register_login/GetBudgetApproved.php"
private val getBudgetRequestUnapprovedUrl: String = "https://albreezetours.com/android_register_login/GetBudgetUnapproved.php"
private val getBudgetRequestReceivedUrl: String = "https://albreezetours.com/android_register_login/GetBudgetReceived.php"
private val updateUsersUrl: String = "https://albreezetours.com/android_register_login/UpdateUsers.php"
private val getUsersNameSolo: String = "https://albreezetours.com/android_register_login/GetUserNamesSolo.php"
private val getGiver: String = "https://albreezetours.com/android_register_login/GetGiver.php"
private val updateBudget: String = "https://albreezetours.com/android_register_login/UpdateBudget.php"
private val getTasksForUsersUrl: String = "https://albreezetours.com/android_register_login/GetTasksForUsers.php"
private val submitTaskUrl: String = "https://albreezetours.com/android_register_login/SubmitTaskAnswer.php"
private val submitTaskDelegatedUrl: String = "https://albreezetours.com/android_register_login/SubmitTaskDelegated.php"

//ID the user will LogIn with and uses to update/receive his data.
var SESSION_ID: String = ""
var SESSION_STATUS: String = ""
var SESSION_NAME: String = ""

open class MySingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MySingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    val imageLoader: ImageLoader by lazy {
        ImageLoader(requestQueue,
            object : ImageLoader.ImageCache {
                private val cache = LruCache<String, Bitmap>(20)
                override fun getBitmap(url: String): Bitmap {
                    return cache.get(url)
                }

                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            })
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    private fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    fun buttonRequest(url: String, button: Button, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                button.text = "${response.substring(0, 20)}"
                progressBar.visibility = View.GONE
            },
            Response.ErrorListener {
                button.text = "Couldn't connect to the internet"
                progressBar.visibility = View.GONE
            }
        )
        addToRequestQueue(stringRequest)
    }
    //Connecting to Database
    fun dbConnect(textView: TextView) {
        val stringRequest = StringRequest(
            Request.Method.GET,
            dbConnectUrl,
            Response.Listener<String> { response ->
                textView.text = "${response.substring(0, 20)}"
            },
            Response.ErrorListener {
                textView.text = "Failed"
            }
        )
        addToRequestQueue(stringRequest)
    }
    fun updateData() {
        val obj = JSONObject()
        obj.put("name", "suzuka")
        obj.put("password", "gozen")
        obj.put("email", "1234@kek@gmail.com")
        obj.put("id", 18)
        val post = JsonObjectRequest(
            Request.Method.POST,
            updateDataUrl,
            obj,
            Response.Listener { response ->

            },
            Response.ErrorListener {

            }
        )
        addToRequestQueue(post)
    }
    fun updateUsers(context: Context, text1: String, text2: String, text3: String, text4: String, text5:String,
                    text6: String, nameBefore: String, lname: String){
        val obj = JSONObject()
        obj.put("name_user", text1)
        obj.put("last_name_user", text2)
        obj.put("user_email", text3)
        obj.put("user_position", text4)
        obj.put("user_status", text5)
        obj.put("status", text6)
        obj.put("nameBefore", nameBefore)
        obj.put("lnameBefore", lname)
        val post = JsonObjectRequest(
            Request.Method.POST,
            updateUsersUrl,
            obj,
            Response.Listener{
                val text: String = "User information updated!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            },
            Response.ErrorListener {error->
            }
        )
        addToRequestQueue(post)
    }
    //Login Menu
    fun insertRegister(
        text1: String, text2: String, text3: String, text4: String,
        text5: String, text6: String
    ) {
        val rootObject = JSONObject()
        rootObject.put("name", text1)
        rootObject.put("lastname", text2)
        rootObject.put("email", text3)
        rootObject.put("position", text4)
        rootObject.put("password", text5)
        rootObject.put("status", text6)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            insertRegister,
            rootObject,
            Response.Listener { response ->
            },
            Response.ErrorListener {
            }
        )
        addToRequestQueue(jsonObject)
    }
    fun businessEmployees(businessName: String, list: ListView, array: ArrayList<BusinessEmployee>,
                            context: Context){
        val rootObject = JSONObject()
        rootObject.put("name", businessName)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            getBusinessEmployeesUrl,
            rootObject,
            { response ->
                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)

                    val text1 = userStuff.getString("employee_first_name")
                    val text2 = userStuff.getString("employee_last_name")
                    val text3 = userStuff.getString("employee_father_name")
                    val text4 = userStuff.getString("employee_position")
                    val text5 = userStuff.getString("employee_start_job")
                    val fullName = "$text1 $text2"
                    val obj1 = BusinessEmployee(fullName, text4, text5)
                    array.add(obj1)
                    val adapter1 = BusinessAdapter(context, array)
                    list.adapter = adapter1
                }
            },
            {
            }
        )

        addToRequestQueue(jsonObject)
    }
    fun submitTaskConfirmation(answer: String, taskid: String, reason: String, number: Int){
        val rootObject = JSONObject()
        rootObject.put("id", SESSION_ID)
        rootObject.put("answer", answer)
        rootObject.put("taskid", taskid)
        rootObject.put("reason", reason)
        rootObject.put("number", number)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            submitTaskUrl,
            rootObject,
            { response ->
            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun returnBackToProgress(userIdTask: String){
        val rootObject = JSONObject()
        rootObject.put("id", userIdTask)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            backToProgressUrl,
            rootObject,
            { response ->

            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun submitDelegatedTask(taskid: String){
        val rootObject = JSONObject()
        rootObject.put("taskid", taskid)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            submitTaskDelegatedUrl,
            rootObject,
            { response ->

            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }
    fun getDelegated2ndUsers(list: ListView, context: Context, tasksList: ArrayList<DelegatedClass>, givenId: String){
        val rootObject = JSONObject()
        rootObject.put("id", givenId)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            GetDelegated2Url,
            rootObject,
            { response ->

                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)

                    val name = userStuff.getString("name_user")
                    val lastname = userStuff.getString("last_name_user")
                    val nameReal = "$name $lastname"
                    val percentage = userStuff.getString("perqindja_complete_user")
                    val idUserTask = userStuff.getString("id_user_task")
                    val status = userStuff.getString("status_task")
                    val document = userStuff.getString("comment_task_user")
                    val document1 = userStuff.getString("reason_reject_user")
                    
                    if(document1 != ""){
                        val obj1 = DelegatedClass(idUserTask, status, nameReal, document1, percentage)
                        tasksList.add(obj1)
                    }
                    else{
                        val obj1 = DelegatedClass(idUserTask, status, nameReal, document, percentage)
                        tasksList.add(obj1)
                    }
                    val adapter1 = DelegatedAdapter(context, tasksList)
                    list.adapter = adapter1
                }
            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun getDelegatedTasks(list: ListView, context: Context, tasksList: ArrayList<TasksInBusiness>){
        val rootObject = JSONObject()
        rootObject.put("id", SESSION_ID)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            getDelegatedUrl,
            rootObject,
            { response ->
                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)

                    val text1 = userStuff.getString("category")
                    val text2 = userStuff.getString("id_task")
                    val text3 = userStuff.getString("title_task")
                    val text4 = userStuff.getString("status_task_finish")
                    val startDate = userStuff.getString("start_date_task")
                    val finishDate = userStuff.getString("end_date_task")
                    val text5 = userStuff.getString("created")
                    val obj1 = TasksInBusiness(text2, text3, text4, text1, "$startDate $finishDate")
                    tasksList.add(obj1)
                    val adapter1 = TaskAdapter(context, tasksList)
                    list.adapter = adapter1
                }
            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }
    fun getTasksForUsers(list: ListView, context: Context, tasksList: ArrayList<TasksInBusiness>){
        val rootObject = JSONObject()
        rootObject.put("id", SESSION_ID)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            getTasksForUsersUrl,
            rootObject,
            { response ->
                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)

                    val text1 = userStuff.getString("category")
                    val text2 = userStuff.getString("id_task")
                    val text3 = userStuff.getString("title_task")
                    val text4 = userStuff.getString("status_task")
                    val startDate = userStuff.getString("start_date_task")
                    val finishDate  = userStuff.getString("end_date_task")
                    val text5 = userStuff.getString("created")

                    val obj1 = TasksInBusiness(text2, text3, text4, text1, "$startDate $finishDate")
                    tasksList.add(obj1)
                    val adapter1 = TaskAdapter(context, tasksList)
                    list.adapter = adapter1
                }
            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun getTasksMenu(given: String, list:ListView, context: Context, tasksList: ArrayList<TasksInBusiness>, number: String){
        val rootObject = JSONObject()
        rootObject.put("business_name", given)
        rootObject.put("number",number)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            getSpecificBusinessTaskUrl,
            rootObject,
            { response ->
                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)

                    val text1 = userStuff.getString("category")
                    val text2 = userStuff.getString("id_task")
                    val text3 = userStuff.getString("title_task")
                    val text4 = userStuff.getString("status_task_finish")
                    val startDate = userStuff.getString("start_date_task")
                    val finishDate = userStuff.getString("end_date_task")
                    val text5 = userStuff.getString("created")
                    val obj1 = TasksInBusiness(text2, text3, text4, text1, "$startDate $finishDate")
                    tasksList.add(obj1)
                    val adapter1 = TaskAdapter(context, tasksList)
                    list.adapter = adapter1
                }
            },
            {
            }
        )

        addToRequestQueue(jsonObject)
    }

    fun getHR(context: Context, list: ListView, business: ArrayList<Movie>){
        val jsonObject = JsonObjectRequest(
            Request.Method.GET,
            getHRUrl,
            null,
            { response ->
                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)
                    val text2 = userStuff.getString("number")
                    val text = userStuff.getString("logo")
                    val text3 = userStuff.getString("business_name_unit")
                    val text4= userStuff.getString("business_username")

                    val textAdd = TextView(context)
                    val numberText = TextView(context)

                    val imageObj = ImageRequest(
                        "https://albreezetours.com/android_register_login/hr-logo/"+"${text}",
                        {response1 ->
                            textAdd.text = text3
                            numberText.text = text2
                            val yeet1 = Movie(response1, textAdd.text.toString(), numberText.text.toString(),text4)
                            business.add(yeet1)
                            val adapter1 = MovieAdapter(context, business)
                            list.adapter = adapter1
                        },
                        200,
                        200,
                        ImageView.ScaleType.CENTER,
                        Bitmap.Config.RGB_565,
                        {
                        }
                    )
                    addToRequestQueue(imageObj)
                }
            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun getUsersAndBusiness(context: Context, list: ListView, taskAdapter: ArrayList<UsersAndBusinessInTask>, id: String,
                            text1: TextView, text2: TextView, text3: TextView, text4: TextView, text5: TextView, text6: TextView){
        val rootObject = JSONObject()
        val nameList: MutableList<String> = mutableListOf()
        val businessList: MutableList<String> = mutableListOf()
        val neededArray: ArrayList<UsersAndBusinessInTask> = ArrayList()

        rootObject.put("id", id)
        rootObject.put("userId", SESSION_ID)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            GetTaskInfoUrl,
            rootObject,
            { response ->
                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                val jsonarry2 = jsonO.getJSONArray("Tasks")
                val jsonarry3 = jsonO.getJSONArray("Business")
                val jsonarry4 = jsonO.getJSONArray("UserCreated")
                val jsonarry5 = jsonO.getJSONArray("Reason")
                for(i in 0 until jsonarry2.length()){
                    val userStuff: JSONObject = jsonarry2.getJSONObject(i)

                    val sDate = userStuff.getString("start_date_task")
                    val eDate = userStuff.getString("end_date_task")
                    val comment = userStuff.getString("comment_task")
                    val userCreated = userStuff.getString("user_create")
                    val priority = userStuff.getString("prioriteti")
                    val delegated = userStuff.getString("delegated")


                    var stringYeet = "<b>Comment:</b> $comment"
                    text1.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))
                    stringYeet = "<b>Priority:</b> $priority"
                    text2.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))
                    stringYeet = "<b>Date:</b> $sDate - $eDate"
                    text3.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))
                    stringYeet = "<b>Delegated:</b> $delegated"
                    text5.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))
                }
                for(i in 0 until jsonarry5.length()){
                    val userStuff: JSONObject = jsonarry5.getJSONObject(i)

                    val reject = userStuff.getString("reason_reject_user")
                    val stringYeet = "<b>Rejected reason:</b> <font color='red'>$reject</font>"
                    text6.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))
                }
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)

                    val name = userStuff.getString("name_user")
                    val lname = userStuff.getString("last_name_user")

                    val needed = "$name $lname"
                    nameList.add(needed)
                }
                for(i in 0 until jsonarry3.length()){
                    val userStuff: JSONObject = jsonarry3.getJSONObject(i)

                    val name = userStuff.getString("business_id")
                    businessList.add(name)
                }
                for(i in 0 until jsonarry4.length()){
                    val userStuff: JSONObject = jsonarry4.getJSONObject(i)

                    val name = userStuff.getString("name_user")
                    val lname = userStuff.getString("last_name_user")
                    var stringYeet = "<b>Created by:</b> $name $lname"
                    text4.text = (HtmlCompat.fromHtml(stringYeet, HtmlCompat.FROM_HTML_MODE_LEGACY))
                }
                if(nameList.size < businessList.size){
                    for(i in 0 until businessList.size - nameList.size){
                        nameList.add("")
                    }

                }
                else{
                    for(i in 0 until nameList.size - businessList.size){
                        businessList.add("")
                    }
                }
                for(i in 0 until nameList.size){
                    val full: String =  nameList[i]
                    val bus: String =  businessList[i]
                    val yeet1 = UsersAndBusinessInTask(full, bus)
                    neededArray.add(yeet1)
                    val adapter1 = UsersAndBusinessAdapter(context, neededArray)
                    list.adapter = adapter1
                }
            },
            {
            }
        )

        addToRequestQueue(jsonObject)
    }

    fun getMoop(context: Context, list: ListView, taskAdapter: ArrayList<TaskMenuList>){
        val jsonObject = JsonObjectRequest(
            Request.Method.GET,
            getMoopUrl,
            null,
            { response ->
                val jsonO: JSONObject = response
                val jsonArry = jsonO.getJSONArray("Users")
                val jsonarry2 = jsonO.getJSONArray("Tasks")

                val dailyArry1 = jsonO.getJSONArray("Tasks Daily Pending")
                val dailyArry2 = jsonO.getJSONArray("Tasks Daily Finished")
                val dailyArry3 = jsonO.getJSONArray("Tasks Daily Progress")
                val dailyArry4 = jsonO.getJSONArray("Tasks Daily Rejected")

                val requested1 = jsonO.getJSONArray("Tasks MonthlyRequested Pending")
                val requested2 = jsonO.getJSONArray("Tasks MonthlyRequested Finished")
                val requested3 = jsonO.getJSONArray("Tasks MonthlyRequested Progress")
                val requested4 = jsonO.getJSONArray("Tasks MonthlyRequested Rejected")

                val required1 = jsonO.getJSONArray("Tasks MonthlyRequired Pending")
                val required2 = jsonO.getJSONArray("Tasks MonthlyRequired Finished")
                val required3 = jsonO.getJSONArray("Tasks MonthlyRequired Progress")
                val required4 = jsonO.getJSONArray("Tasks MonthlyRequired Rejected")
                for(i in 0 until jsonArry.length()){
                    val userStuff: JSONObject = jsonArry.getJSONObject(i)
                    val taskStuff: JSONObject = jsonarry2.getJSONObject(i)
                    val dailyPending1: JSONObject = dailyArry1.getJSONObject(i)
                    val dailyFinished1: JSONObject = dailyArry2.getJSONObject(i)
                    val dailyProgress1: JSONObject = dailyArry3.getJSONObject(i)
                    val dailyRejected1: JSONObject = dailyArry4.getJSONObject(i)
                    val requestedPending1: JSONObject = requested1.getJSONObject(i)
                    val requestedFinished1: JSONObject = requested2.getJSONObject(i)
                    val requestedProgress1: JSONObject = requested3.getJSONObject(i)
                    val requestedRejected1: JSONObject = requested4.getJSONObject(i)
                    val requiredPending1: JSONObject = required1.getJSONObject(i)
                    val requiredFinished1: JSONObject = required2.getJSONObject(i)
                    val requiredProgress1: JSONObject = required3.getJSONObject(i)
                    val requiredRejected1: JSONObject = required4.getJSONObject(i)

                    val text = userStuff.getString("logo")
                    val text2 = "Total ${taskStuff.getString("number")} tasks"
                    val text3 = userStuff.getString("business_name")
                    val text4= userStuff.getString("business_username")

                    val dailyPending = dailyPending1.getString("Task_Pending")
                    val dailyFinished = dailyFinished1.getString("Task_Finished")
                    val dailyProgress = dailyProgress1.getString("Task_Progress")
                    val dailyReject = dailyRejected1.getString("Task_Rejected")

                    val requestPending = requestedPending1.getString("Task_Pending")
                    val requestFinished = requestedFinished1.getString("Task_Finished")
                    val requestProgress= requestedProgress1.getString("Task_Progress")
                    val requestReject= requestedRejected1.getString("Task_Rejected")

                    val requirePending = requiredPending1.getString("Task_Pending")
                    val requireFinished = requiredFinished1.getString("Task_Finished")
                    val requireProgress = requiredProgress1.getString("Task_Progress")
                    val requireReject= requiredRejected1.getString("Task_Rejected")

                    val textAdd = TextView(context)
                    val numberText = TextView(context)

                    val imageObj = ImageRequest(
                        "https://albreezetours.com/android_register_login/logo/"+"${text}",
                        {response1 ->
                            textAdd.text = text3
                            numberText.text = text2
                            val yeet1 = TaskMenuList(response1, textAdd.text.toString(), numberText.text.toString(),text4, dailyPending, dailyFinished,dailyProgress,dailyReject
                            ,requestPending,requestFinished,requestProgress,requestReject,requirePending,requireFinished,requireProgress,requireReject)
                            taskAdapter.add(yeet1)
                            val adapter1 = TaskMenuAdapter(context, taskAdapter)
                            list.adapter = adapter1
                        },
                        200,
                        200,
                        ImageView.ScaleType.CENTER,
                        Bitmap.Config.RGB_565,
                        {
                        }
                    )
                    addToRequestQueue(imageObj)
                }

            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun createTask(
        text1: String, text2: String, text3: String, text4: String,
        text5: String, text6: String, text7: String, text8: MutableList<String>, text9: MutableList<String>
    ) {
        val rootObject = JSONObject()
        val names = JSONArray()
        for(i in 0 until text8.size){
            names.put(text8[i])
        }
        val business = JSONArray()
        for(i in 0 until text9.size){
            business.put(text9[i])
        }
        rootObject.put("title_task", text1)
        rootObject.put("priority", text2)
        rootObject.put("start_date_task", text3)
        rootObject.put("end_date_task", text4)
        rootObject.put("category", text5)
        rootObject.put("delegated", text6)
        rootObject.put("comment_task", text7)
        rootObject.put("id", SESSION_ID)
        rootObject.put("names", names)
        rootObject.put("businesses", business)


        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            createTaskUrl,
            rootObject,
            { response ->
            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun logIn(context: Context, email: String, password: String) {
        val obj1 = JSONObject()
        obj1.put("email", email)
        obj1.put("password", password)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            logInUrl,
            obj1,
            { response ->
                val jsonObj: JSONObject = response
                SESSION_ID = jsonObj.getString("id")
                SESSION_STATUS = jsonObj.getString("user_status")
                SESSION_NAME = jsonObj.getString("name")

                if (SESSION_STATUS == "User" || SESSION_STATUS == "Super Users"){
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
                else if(SESSION_STATUS == "Administrator"){
                    val intent = Intent(context, AdministratorMenu::class.java)
                    context.startActivity(intent)
                }
            },
            {error->
                val text = "Incorrect email or password!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        )
        addToRequestQueue(jsonObject)
    }
    //RequestBudget
    fun requestBudget(title: String, amount:Int, businessId: String, businessName: String, reason: String,
                        deposit: String, giver: String, description: String, data: String, userId: String, userName: String){
        val obj1 = JSONObject()
        obj1.put("title", title)
        obj1.put("amount", amount)
        obj1.put("userId", userId)
        obj1.put("user", userName)
        obj1.put("businessId", businessId)
        obj1.put("business", businessName)
        obj1.put("reason",reason)
        obj1.put("deposit", deposit)
        obj1.put("giver", giver)
        obj1.put("description", description)
        obj1.put("data", data)

        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            requestBudget,
            obj1,
            { response ->
            },
            {
            }
        )
        addToRequestQueue(jsonObject)
    }
    fun getGiver(id: Int, textview: TextView, textview2: EditText, textview3: EditText){
        val obj1 = JSONObject()
        obj1.put("id",id)
        var answer: String = ""

        val request = JsonObjectRequest(
            Request.Method.POST,
            getGiver,
            obj1,
            { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)
                    textview.text = userStuff.getString("gave")
                    textview2.setText(userStuff.getString("titull_request").toString())
                    textview3.setText(userStuff.getString("description").toString())
                }
            }, { error->
            }
        )
        addToRequestQueue(request)
    }
    fun updateBudget(rId: Int, text1: String, text2: String, text3: String, text4: String, text5: String, text6: String){
        val obj1 = JSONObject()
        obj1.put("id",rId)
        obj1.put("amount", text1)
        obj1.put("title", text2)
        obj1.put("date", text3)
        obj1.put("description", text4)
        obj1.put("reason_reject", text5)
        obj1.put("status", text6)
        val request = JsonObjectRequest(
            Request.Method.POST,
            updateBudget,
            obj1,
            { response ->
            }, { error->
            }
        )
        addToRequestQueue(request)
    }
    //Inserting Data in DB DailyTransaction 1-2
    fun getData12(
        list: ListView,
        applicationContext: Context,
        yeet: MutableList<String>,
        adapter: ArrayAdapter<String>
    ) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            getDataUrl,
            null,
            { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)
                    val willWrite = IncomeDetails(
                        userStuff.getString("date"),
                        userStuff.getString("receivedFrom"),
                        userStuff.getString("reference"),
                        userStuff.getInt("price/unit"),
                        userStuff.getInt("quantity"),
                        userStuff.getInt("total")
                    ).toString()
                    yeet.add(willWrite)
                }
                list.adapter = adapter
            }, {
            }
        )
        addToRequestQueue(request)
    }
    //1-4
    fun insertData14(
        text1: String,
        text2: String,
        text3: String,
        text4: String,
        text5: String,
        text6: String,
        text7: String
    ) {
        val rootObject = JSONObject()
        rootObject.put("date", text1)
        rootObject.put("description", text2)
        rootObject.put("reference", text3)
        rootObject.put("department", text4)
        rootObject.put("cashIn", text5)
        rootObject.put("cashOut", text6)
        rootObject.put("balance", text7)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            insertDataUrl14,
            rootObject,
            Response.Listener { response ->
            },
            Response.ErrorListener {
            }
        )
        addToRequestQueue(jsonObject)
    }

    //1-4
    fun getData14(
        list: ListView,
        applicationContext: Context,
        yeet: MutableList<String>,
        adapter: ArrayAdapter<String>
    ) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            getDataUrl14,
            null,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")

                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = addCashBook(
                        userStuff.getString("date"),
                        userStuff.getString("description"),
                        userStuff.getString("reference"),
                        userStuff.getString("department"),
                        userStuff.getInt("cashIn"),
                        userStuff.getInt("cashOut"),
                        userStuff.getInt("balance")
                    ).toString()
                    yeet.add(willWrite)
                }
                list.adapter = adapter

            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)

    }
    fun getUserDetails( list: ListView, applicationContext: Context, yeet: MutableList<CharSequence>,
                        adapter: ArrayAdapter<CharSequence>){
        val request = JsonObjectRequest(
            Request.Method.GET,
            getUserDetailsUrl,
            null,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")

                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)
                    val willWrite = UserDetails(
                        userStuff.getString("name_user"),
                        userStuff.getString("last_name_user"),
                        userStuff.getString("user_status"),
                        userStuff.getString("user_email"),
                        applicationContext
                    ).toString1()
                    yeet.add(willWrite)
                }
                list.adapter = adapter

            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }
    fun getBudgetRequestList(list: ListView, applicationContext: Context, yeet: MutableList<CharSequence>,
                             adapter: ArrayAdapter<CharSequence>){

        val request = JsonObjectRequest(
            Request.Method.GET,
            getBudgetRequestListUrl,
            null,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")

                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = BudgetList(
                        userStuff.getString("id_budget"),
                        userStuff.getString("request"),
                        userStuff.getString("business"),
                        userStuff.getString("sum_budget"),
                        applicationContext
                    ).toString1()
                    yeet.add(willWrite)
                }
                list.adapter = adapter
            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }
    fun getBudgetApproved(list: ListView, applicationContext: Context, yeet: MutableList<CharSequence>,
                          adapter: ArrayAdapter<CharSequence>){

            val request = JsonObjectRequest(
                Request.Method.GET,
                getBudgetRequestApprovedUrl,
                null,
                Response.Listener { response ->
                    val jsonObj: JSONObject = response
                    val jsonArray: JSONArray = jsonObj.getJSONArray("Users")

                    for (i in 0 until jsonArray.length()) {
                        val userStuff: JSONObject = jsonArray.getJSONObject(i)

                        val willWrite = BudgetList(
                            userStuff.getString("id_budget"),
                            userStuff.getString("request"),
                            userStuff.getString("business"),
                            userStuff.getString("sum_budget"),
                            applicationContext
                        ).toString1()
                        yeet.add(willWrite)
                    }
                    list.adapter = adapter
                }, Response.ErrorListener {
                }
            )
            addToRequestQueue(request)
        }
    fun getBudgetUnapproved(list: ListView, applicationContext: Context, yeet: MutableList<CharSequence>,
                          adapter: ArrayAdapter<CharSequence>){
        val request = JsonObjectRequest(
            Request.Method.GET,
            getBudgetRequestUnapprovedUrl,
            null,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")

                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = BudgetList(
                        userStuff.getString("id_budget"),
                        userStuff.getString("request"),
                        userStuff.getString("business"),
                        userStuff.getString("sum_budget"),
                        applicationContext
                    ).toString1()
                    yeet.add(willWrite)
                }
                list.adapter = adapter
            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }
    fun getBudgetReceived(list: ListView, applicationContext: Context, yeet: MutableList<CharSequence>,
                            adapter: ArrayAdapter<CharSequence>){
        val request = JsonObjectRequest(
            Request.Method.GET,
            getBudgetRequestReceivedUrl,
            null,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")

                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = BudgetList(
                        userStuff.getString("id_budget"),
                        userStuff.getString("request"),
                        userStuff.getString("business"),
                        userStuff.getString("sum_budget"),
                        applicationContext
                    ).toString1()
                    yeet.add(willWrite)
                }
                list.adapter = adapter
            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }

    fun getBudgetNames(spinner: Spinner, arrayAdapter: ArrayAdapter<String>, nameList: MutableList<String>){

        val obj = JSONObject()
        obj.put("id", SESSION_ID)
        val request = JsonObjectRequest(
            Request.Method.POST,
            getUsersNameSolo,
            obj,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = GetUsersNameAndLastName(
                        userStuff.getString("name_user"),
                        userStuff.getString("last_name_user")
                    ).toString()
                    nameList.add(willWrite)
                }
                spinner.adapter = arrayAdapter

            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }

    fun getUsersNames(spinner: Spinner, arrayAdapter: ArrayAdapter<String>, nameList: MutableList<String>, user_status: String) {
        if(user_status == "User" || user_status == "SuperUsers") {
            val obj = JSONObject()
            obj.put("id", SESSION_ID)
            val request = JsonObjectRequest(
                Request.Method.POST,
                getUsersName,
                obj,
                Response.Listener { response ->
                    val jsonObj: JSONObject = response
                    val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                    for (i in 0 until jsonArray.length()) {
                        val userStuff: JSONObject = jsonArray.getJSONObject(i)

                        val willWrite = GetUsersNameAndLastName(
                            userStuff.getString("name_user"),
                            userStuff.getString("last_name_user")
                        ).toString()
                        nameList.add(willWrite)
                    }
                    spinner.adapter = arrayAdapter

                }, Response.ErrorListener {
                }
            )
            addToRequestQueue(request)
        }
        else{
            val request = JsonObjectRequest(
                Request.Method.GET,
                getUsersName,
                null,
                Response.Listener { response ->
                    val jsonObj: JSONObject = response
                    val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                    for (i in 0 until jsonArray.length()) {
                        val userStuff: JSONObject = jsonArray.getJSONObject(i)

                        val willWrite = GetUsersNameAndLastName(
                            userStuff.getString("name_user"),
                            userStuff.getString("last_name_user")
                        ).toString()

                        nameList.add(willWrite)
                    }
                    spinner.adapter = arrayAdapter

                }, Response.ErrorListener {
                }
            )
            addToRequestQueue(request)
        }
    }
    fun getUserId(name: String, id: TextView){
        var first_name: String = ""
        var last_name: String = ""
        var keepInMind: Int = 0
        for(i in 0 until name.length){
            if(name[i] == ' '){
                keepInMind = 1
            }
            if(keepInMind == 0){
                first_name += name[i]
            }
            else if(keepInMind == 1 && name[i] != ' '){
                last_name += name[i]
            }

        }
        val obj1 = JSONObject()
        obj1.put("name", first_name)
        obj1.put("lastname", last_name)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            getUserID,
            obj1,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                id.text = jsonObj.getString("id")
            },
            Response.ErrorListener {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun getBusinessId(name: String, id: TextView){
        val obj1 = JSONObject()
        obj1.put("businessName", name)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            getBusinessID,
            obj1,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                id.text = jsonObj.getString("id")
            },
            Response.ErrorListener {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun getBusinessNames(spinner: Spinner, arrayAdapter: ArrayAdapter<String>,nameList: MutableList<String>){
        val request = JsonObjectRequest(
            Request.Method.GET,
            getBusinessNamesBudget,
            null,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = GetUsersName(
                        userStuff.getString("name_business")
                    ).toString()

                    nameList.add(willWrite)
                }
                spinner.adapter = arrayAdapter

            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }
    fun getReason(spinner:Spinner, arrayAdapter: ArrayAdapter<String>, nameList: MutableList<String>) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            getReason,
            null,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = GetUsersName(
                        userStuff.getString("title_reason")
                    ).toString()
                    nameList.add(willWrite)
                }
                spinner.adapter = arrayAdapter
            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }
    fun getSpecificReason(id:Int, text1: TextView) {
        val obj1 = JSONObject()
        obj1.put("id",id)
        val request = JsonObjectRequest(
            Request.Method.POST,
            getSpecificReason,
            obj1,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = GetUsersName(
                        userStuff.getString("reason")
                    ).toString()
                    text1.text = willWrite
                }
            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }
    fun verifyPendingStatus(value: ImageButton){
        val request = StringRequest(
            Request.Method.GET,
            verifyPendingStatus,
            Response.Listener { response ->
                val yeet: String = response
                if(yeet == "1"){
                    value.setImageResource(R.mipmap.ic_pending_ring_foreground)
                }
                else{
                    value.setImageResource(R.mipmap.ic_pending_foreground)
                }
            }, Response.ErrorListener {
            }
        )
        addToRequestQueue(request)
    }
    fun verifyPendingStatus1(value: ImageButton){
        val rootObject = JSONObject()
        rootObject.put("id", SESSION_ID)
        val request = JsonObjectRequest(
            Request.Method.POST,
            verifyPendingStatus1,
            rootObject,
            { response ->
                val yeet: JSONObject = response
                val number1 = yeet.getString("Users")
                if(number1 == "1"){
                    value.setImageResource(R.mipmap.ic_pending_ring_foreground)
                }
                else{
                    value.setImageResource(R.mipmap.ic_pending_foreground)
                }
            }, {
            }
        )
        addToRequestQueue(request)
    }
}
package com.example.businessbuilder

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.LruCache
import android.view.View
import android.widget.*
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

private val dbConnectUrl: String = "https://albreezetours.com/android_register_login/Connection.php"
private val insertDataUrl: String = "https://albreezetours.com/android_register_login/InsertData.php"
private val getDataUrl: String = "https://albreezetours.com/android_register_login/GetData.php"
private val updateDataUrl: String = "https://albreezetours.com/android_register_login/UpdateData.php"
private val getDataUrl14: String = "https://albreezetours.com/android_register_login/GetData14.php"
private val getUserDetailsUrl: String = "https://albreezetours.com/android_register_login/GetUserDetails.php"
private val insertDataUrl14: String = "https://albreezetours.com/android_register_login/InsertData14.php"
private val createTaskUrl: String = "https://albreezetours.com/android_register_login/InsertTask.php"
private val insertRegister: String = "https://albreezetours.com/android_register_login/Register.php"
private val logInUrl: String = "https://albreezetours.com/android_register_login/Login.php"
private val getUsersName : String = "https://albreezetours.com/android_register_login/GetUsersName.php"
private val getBusinessNames: String = "https://albreezetours.com/android_register_login/GetBusinessNames.php"
private val getReason: String = "https://albreezetours.com/android_register_login/GetReason.php"
private val getSpecificReason: String = "https://albreezetours.com/android_register_login/GetSpecificReason.php"
private val verifyPendingStatus: String = "https://albreezetours.com/android_register_login/VerifyPendingImage.php"
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

    fun createTask(
        text1: String, text2: String, text3: String, text4: String,
        text5: String, text6: String, text7: String
    ) {
        val rootObject = JSONObject()
        rootObject.put("title_task", text1)
        rootObject.put("priority", text2)
        rootObject.put("start_date_task", text3)
        rootObject.put("end_date_task", text4)
        rootObject.put("category", text5)
        rootObject.put("delegated", text6)
        rootObject.put("comment_task", text7)
        rootObject.put("id", SESSION_ID)

        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            createTaskUrl,
            rootObject,
            Response.Listener { response ->
            },
            Response.ErrorListener {
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
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                SESSION_ID = jsonObj.getString("id")
                SESSION_STATUS = jsonObj.getString("user_status")
                SESSION_NAME = jsonObj.getString("name")
            },
            Response.ErrorListener {error->
                val text = "Incorrect email or password!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        )
        addToRequestQueue(jsonObject)
    }
    //RequestBudget
    fun requestBudget(title: String, amount:Int, businessId: Int, businessName: String, reason: String,
                        deposit: String, giver: String, description: String, data: String, userId: Int, userName: String){
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
            Response.Listener { response ->
            },
            Response.ErrorListener {
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
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")
                for (i in 0 until jsonArray.length()) {
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)
                    textview.text = userStuff.getString("gave")
                    textview2.setText(userStuff.getString("titull_request").toString())
                    textview3.setText(userStuff.getString("description").toString())
                }
            }, Response.ErrorListener {error->
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
            Response.Listener { response ->
            }, Response.ErrorListener {error->
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
            Response.Listener { response ->
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
            }, Response.ErrorListener {
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



    fun getUsersNames(spinner: Spinner, arrayAdapter: ArrayAdapter<String>, nameList: MutableList<String>, user_status: String) {
        if(user_status == "User" || user_status == "SuperUsers") {
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
    fun getUserId(name: String){
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
                USER_ID = jsonObj.getString("id").toInt()

            },
            Response.ErrorListener {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun getBusinessId(name: String){
        var answer12: String = ""
        val obj1 = JSONObject()
        obj1.put("businessName", name)
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            getBusinessID,
            obj1,
            Response.Listener { response ->
                val jsonObj: JSONObject = response
                BUSINESS_ID = jsonObj.getString("id").toInt()
            },
            Response.ErrorListener {
            }
        )
        addToRequestQueue(jsonObject)
    }

    fun getBusinessNames(spinner: Spinner, arrayAdapter: ArrayAdapter<String>,nameList: MutableList<String>){
        val request = JsonObjectRequest(
            Request.Method.GET,
            getBusinessNames,
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

}









package com.example.businessbuilder

import android.content.Context
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

val dbConnectUrl: String = "https://albreezetours.com/android_register_login/Connection.php"
val insertData: String = "https://albreezetours.com/android_register_login/InsertData.php"
val getData: String = "https://albreezetours.com/android_register_login/GetData.php"

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
            })
        addToRequestQueue(stringRequest)
    }

    //Connecting to Database
    fun dbConnect(url:String, textView: TextView){
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String>{response ->
                textView.text = "${response.substring(0,20)}"
            },
            Response.ErrorListener {
                textView.text = "Failed"
            })
        addToRequestQueue(stringRequest)
    }
    //Inserting Data in DB
    fun insertData(url: String){
        val rootObject = JSONObject()
        rootObject.put("name", "su")
        rootObject.put("password","ijasdiasj")
        rootObject.put("email","tara@hotmail.com")
        val jsonObject = JsonObjectRequest(
            Request.Method.POST,
            url,
            rootObject,
            Response.Listener{response->

            },
            Response.ErrorListener {

            }
        )
        addToRequestQueue(jsonObject)
    }

    //Parsing JSON Data
    fun getData(url:String, list: ListView, applicationContext: Context){

        val yeet = mutableListOf<String>()
        val adapter:  ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, yeet)

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener{response->
                val jsonObj: JSONObject = response
                val jsonArray: JSONArray = jsonObj.getJSONArray("Users")

                for(i in 0 until jsonArray.length()){
                    val userStuff: JSONObject = jsonArray.getJSONObject(i)

                    val willWrite = Users(
                        userStuff.getString("name"),
                        userStuff.getString("password"),
                        userStuff.getString("email")
                    ).toString()
                    yeet.add(willWrite)
                }
                list.adapter = adapter

            },Response.ErrorListener {
            })
        addToRequestQueue(request)
    }
}
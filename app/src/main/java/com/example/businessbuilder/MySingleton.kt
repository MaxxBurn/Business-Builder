package com.example.businessbuilder

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

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
    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>) {
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
                })
            addToRequestQueue(stringRequest)
        }
}
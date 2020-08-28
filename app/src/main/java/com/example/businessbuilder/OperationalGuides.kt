package com.example.businessbuilder

import com.example.businessbuilder.R
import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager


class MovieAdapter(context: Context, list: ArrayList<Movie>) :
    ArrayAdapter<Movie?>(context, 0, list as List<Movie?>) {
    private var mContext: Context
    private var moviesList: List<Movie> = ArrayList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem: View? = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.item_custom, parent, false)
        val currentMovie = moviesList[position]
        val image: ImageView = listItem?.findViewById(R.id.imageView_poster) as ImageView
        image.setImageBitmap(currentMovie.getmImageDrawable())
        val name = listItem.findViewById(R.id.textView_name) as TextView
        name.text = currentMovie.getmName()
        val release = listItem.findViewById(R.id.textView_release) as TextView
        release.text = currentMovie.getmRelease()
        return listItem
    }

    init {
        moviesList = list
        mContext = context;
    }
}


class Movie(
    private var mImageDrawable: Bitmap,
    private var mName: String, var mRelease: String
) {

    fun getmImageDrawable(): Bitmap {
        return mImageDrawable
    }

    fun setmImageDrawable(mImageDrawable: Bitmap) {
        this.mImageDrawable = mImageDrawable
    }

    fun getmName(): String {
        return mName
    }

    fun setmName(mName: String) {
        this.mName = mName
    }

    fun getmRelease(): String {
        return mRelease
    }

    fun setmRelease(mRelease: String) {
        this.mRelease = mRelease
    }

    // Constructor that is used to create an instance of the Movie object
    init {
        mName = mName
        this.mRelease = mRelease
    }
}



class OperationalGuides : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operational_guides)

        val listView = findViewById<ListView>(R.id.movies_list)
        val moviesList : ArrayList<Movie> = ArrayList()

        MySingleton.getInstance(this).getHR(this, listView, moviesList)

    }
}
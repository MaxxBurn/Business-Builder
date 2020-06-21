package com.example.businessbuilder

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

open class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    val dbHelper = Database(context)
    val db = dbHelper.writableDatabase
    val values = ContentValues().apply {
        put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "creditSales")
        put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "companyName")
        put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "description")
        put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "addressLine")
        put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "subTotal")
        put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "taxRate")
    }
    val newRodId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values)

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Database.db"
    }
    object FeedReaderContract {
        // Table contents are grouped together in an anonymous object.
        object FeedEntry : BaseColumns {
            const val TABLE_NAME = "entry"
            const val COLUMN_NAME_TITLE = "title"
            const val COLUMN_NAME_SUBTITLE = "subtitle"
        }
    }
        private  val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE} TEXT," +
                    "${FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"
}
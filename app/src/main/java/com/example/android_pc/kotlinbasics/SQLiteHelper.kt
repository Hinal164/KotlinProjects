package com.example.android_pc.kotlinbasics

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIOM) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID Integer PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AGE Integer, $COLUMN_MOBNO Integer, $COLUMN_EMAIL TEXT,$COLUMN_IMAGE TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun addUser(user: User): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, user.name)
        values.put(COLUMN_AGE, user.age)
        values.put(COLUMN_MOBNO, user.mob)
        values.put(COLUMN_EMAIL, user.email)
        values.put(COLUMN_IMAGE,user.image)

        val _success = db.insert(TABLE_NAME, null, values)
        db.close()

        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun fetchRecord(): List<User> {
        val userList = ArrayList<User>()
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserTable", null)
        var user: User? = null
        if (cursor.moveToFirst()) {
            do {
                user = User()
                user.id=cursor.getString(0).toInt()
                user.name=cursor.getString(1)
                user.age=cursor.getString(2).toInt()
                user.mob=cursor.getString(3).toLong()
                user.email=cursor.getString(4)
                user.image=cursor.getString(5)
                userList.add(user)
            } while (cursor.moveToNext())
        }
        for (mo in userList) {

            Log.i("Hello", "" + mo.name)
        }
        cursor.close()
        sqLiteDatabase.close()
        return userList
    }

    fun updateRecord(user:User) {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, user.id)
        contentValues.put(COLUMN_NAME, user.name)
        contentValues.put(COLUMN_AGE, user.age)
        contentValues.put(COLUMN_MOBNO, user.mob)
        contentValues.put(COLUMN_EMAIL, user.email)
        contentValues.put(COLUMN_EMAIL, user.image)
        sqLiteDatabase.update(TABLE_NAME, contentValues, "$COLUMN_ID = ?", arrayOf(user.id.toString()))
        sqLiteDatabase.close()
    }

    fun deleteRecord(userId: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID  = ?", arrayOf(userId.toString()))
        db.close()
    }
    companion object {
        private val DB_NAME = "UserDatabase"
        private val DB_VERSIOM = 2
        private val TABLE_NAME = "UserTable"
        private val COLUMN_ID = "ID"
        private val COLUMN_NAME = "NAME"
        private val COLUMN_AGE = "AGE"
        private val COLUMN_MOBNO = "MOBNO"
        private val COLUMN_EMAIL = "EMAIL"
        private val COLUMN_IMAGE = "IMAGE"

    }
}
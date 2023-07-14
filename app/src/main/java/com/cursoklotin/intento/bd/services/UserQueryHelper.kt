package com.cursoklotin.intento.bd.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor
import com.cursoklotin.intento.models.UserData
import android.util.Log
import com.cursoklotin.intento.bd.DatabaseHelper

class UserQueryHelper(private val context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper.getInstance(context)
    private val db: SQLiteDatabase = dbHelper.readableDatabase

    fun getUserById(userId: Int): UserData? {
        return null
    }



}

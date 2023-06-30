package com.cursoklotin.intento.bd.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor
import com.cursoklotin.intento.models.UserData
import android.util.Log

class UserQueryHelper(private val db: SQLiteDatabase) {
    fun getUserById(userId: Int): UserData? {
        return null
    }



}

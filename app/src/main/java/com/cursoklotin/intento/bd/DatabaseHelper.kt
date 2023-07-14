package com.cursoklotin.intento.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.cursoklotin.intento.bd.insertion.DataInsertionHelper
import com.cursoklotin.intento.bd.tables.TableCreationHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "boletita22222a.db"
        private const val DATABASE_VERSION = 18
        @Volatile
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            return instance ?: synchronized(this) {
                instance ?: DatabaseHelper(context.applicationContext).also { instance = it }
            }
        }

    }

    override fun onCreate(db: SQLiteDatabase) {
        val tableCreationHelper = TableCreationHelper(db)
        tableCreationHelper.createTables()

        val dataInsertionHelper = DataInsertionHelper(db)
        dataInsertionHelper.insertDefaultData()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Actualiza la base de datos si se cambia la versión
        db.execSQL("DROP TABLE IF EXISTS Usuario")
        db.execSQL("DROP TABLE IF EXISTS Cargo")
        db.execSQL("DROP TABLE IF EXISTS Empleado")
        db.execSQL("DROP TABLE IF EXISTS Horario")
        db.execSQL("DROP TABLE IF EXISTS Asistencia")
        db.execSQL("DROP TABLE IF EXISTS QR")
        db.execSQL("DROP TABLE IF EXISTS Boleta")
        onCreate(db)
    }
}




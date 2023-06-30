package com.cursoklotin.intento.utils

import java.util.Calendar
import java.text.SimpleDateFormat
import android.util.Log
import java.util.TimeZone


object DateTimeUtils {
    fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("America/Lima")
        val currentDateTime = dateFormat.format(calendar.time)

        Log.d("DateTimeUtils", "Current DateTime: $currentDateTime")

        return currentDateTime
    }

    fun getCurrentDate(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("America/Lima")
        return calendar
    }

    fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("America/Lima")
        val currentTime = dateFormat.format(calendar.time)

        Log.d("DateTimeUtils", "Current Time: $currentTime")

        return currentTime
    }


}



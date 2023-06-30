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
}

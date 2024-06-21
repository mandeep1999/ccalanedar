package com.example.ccalanedar.calendar.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



object Utility {

    fun isValidString(text: String?): Boolean {
        return text.isNullOrBlank().not()
    }

    fun convertTimestampToReadableDate(timestamp: Long): String {
        val date: Date = Date(timestamp)
        val dateFormat: SimpleDateFormat =
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(date)
    }
}
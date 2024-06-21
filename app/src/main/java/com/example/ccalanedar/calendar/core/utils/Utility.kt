package com.example.ccalanedar.calendar.core.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
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

    fun checkSameDay(timestamp1: Long, timestamp2: Long): Boolean {
        // Convert long timestamps to LocalDateTime
        val dateTime1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp1), ZoneId.systemDefault())
        val dateTime2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp2), ZoneId.systemDefault())

        // Compare the dates
        val date1 = dateTime1.toLocalDate()
        val date2 = dateTime2.toLocalDate()

        return date1 == date2
    }
}
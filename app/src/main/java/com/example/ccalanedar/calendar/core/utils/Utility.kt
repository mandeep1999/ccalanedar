package com.example.ccalanedar.calendar.core.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale



object Utility {

    /**
     * @param text -> if text is non-null and contain non white spaces then @return true
     * otherwise @return false
     */
    fun isValidString(text: String?): Boolean {
        return text.isNullOrBlank().not()
    }

    /**
     * @param timestamp -> epoch time stamp
     * @return the readable string for the date from time stamp
     */
    fun convertTimestampToReadableDate(timestamp: Long): String {
        val date: Date = Date(timestamp)
        val dateFormat: SimpleDateFormat =
            SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(date)
    }

    /**
     * @param timestamp1 -> first timestamp
     * @param timestamp2 -> second timestamp
     * if both the timestamps are falling in the same day, @return true otherwise @return false
     */
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
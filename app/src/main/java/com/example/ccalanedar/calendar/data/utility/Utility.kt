package com.example.ccalanedar.calendar.data.utility

import com.example.ccalanedar.calendar.core.utils.Utility

object Utility {

    fun isValidTask(title: String?, description: String?, date: Long?): Boolean {
        return !(Utility.isValidString(title).not() || Utility.isValidString(description)
            .not() || date == null)
    }

    fun isValidTaskId(taskId: Int?): Boolean {
        return taskId != null
    }
}
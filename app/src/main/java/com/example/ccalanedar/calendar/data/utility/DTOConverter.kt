package com.example.ccalanedar.calendar.data.utility

import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.calendar.data.response.TaskItem

object DTOConverter {

    fun convertTaskItemToTaskDTO(taskItem: TaskItem): TaskModelDTO? {

        if (taskItem.taskID == null) {
            return null
        }

        return TaskModelDTO(
            taskId = taskItem.taskID,
            title = taskItem.taskDetail?.title,
            description = taskItem.taskDetail?.description,
            date = taskItem.taskDetail?.date
        )
    }
}
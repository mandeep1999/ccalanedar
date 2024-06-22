package com.example.ccalanedar.calendar.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO

@Dao
interface CalendarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskIntoDB(taskModelDTO: TaskModelDTO)

    @Query("select * from tasks_list")
    fun getAllTasksFromDB(): LiveData<List<TaskModelDTO>>

    @Query("delete from tasks_list where task_id = :taskId")
    suspend fun deleteTaskFromDB(taskId: Int)

    @Query("delete from tasks_list")
    suspend fun deleteAllTasks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBulkTask(list: List<TaskModelDTO>)

    @Transaction
    suspend fun refreshTasksInTransaction(newTasks: List<TaskModelDTO>) {
        deleteAllTasks()
        insertBulkTask(newTasks)
    }


}
package com.example.ccalanedar.calendar.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface CalendarDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskIntoDB(taskModelDTO: TaskModelDTO)

    @Query("select * from tasks_list")
    fun getAllTasksFromDB(): Flow<TaskModelDTO>

    @Query("delete from tasks_list where task_id = :taskId")
    suspend fun deleteTaskFromDB(taskId: Int)
}
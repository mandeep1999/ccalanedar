package com.example.ccalanedar.calendar.core.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ccalanedar.calendar.data.db.dao.CalendarDAO
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO

@Database(
    entities = [TaskModelDTO::class],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {

    abstract val calendarDAO: CalendarDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "calendar_app"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
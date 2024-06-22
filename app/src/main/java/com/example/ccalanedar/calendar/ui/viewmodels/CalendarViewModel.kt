package com.example.ccalanedar.calendar.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccalanedar.calendar.domain.data.CalendarUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CalendarViewModel @Inject constructor(private val calendarUseCases: CalendarUseCases) :
    ViewModel() {

    private val _showDataLoader: MutableLiveData<Boolean> = MutableLiveData(false)
    val showDataLoader get(): LiveData<Boolean> = _showDataLoader

    var newTaskTitle: String? = null
    var newTaskDescription: String? = null
    var newTaskDate: Long? = null

    init {
        fetchAllTasksFromServer()
    }

    /**
     * Function to be called to fetch all the tasks from the server.
     * It is called once, when the view model is initialised.
     */
    private fun fetchAllTasksFromServer() {
        _showDataLoader.value = true
        viewModelScope.launch(Dispatchers.IO) {
            calendarUseCases.fetchAllTaskUseCase()
            withContext(Dispatchers.Main){
                _showDataLoader.value = false
            }
        }
    }

    /**
     * Function to get all tasks from local room db. It returns a live date
     * with all the tasks.
     */
    fun getAllTasks() = calendarUseCases.readAllTaskUseCase()

    /**
     * Function to clear all the variables required for creating a new task.
     * this is done when the bottom sheet is dismissed.
     */
    fun clearNewTask() {
        newTaskTitle = null
        newTaskDescription = null
        newTaskDate = null
    }

    /**
     * Function to create a new task.
     */
    fun createNewTask() {
        _showDataLoader.value = true
        viewModelScope.launch(Dispatchers.IO) {
            calendarUseCases.insertNewTaskUseCase(
                date = newTaskDate,
                title = newTaskTitle,
                description = newTaskDescription
            )
            withContext(Dispatchers.Main) {
                _showDataLoader.value = false
            }
        }
    }

    /**
     * Function to delete an existing task.
     * @param id -> the task id to be deleted.
     */
    fun deleteTask(id: Int) {
        _showDataLoader.value = true
        viewModelScope.launch {
            calendarUseCases.deleteTaskUseCase(id)
            withContext(Dispatchers.Main) {
                _showDataLoader.value = false
            }
        }
    }

}
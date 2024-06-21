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


    init {
        fetchAllTasksFromServer()
    }

    private fun fetchAllTasksFromServer() {
        _showDataLoader.value = true
        viewModelScope.launch(Dispatchers.IO) {
            calendarUseCases.fetchAllTaskUseCase()
            withContext(Dispatchers.Main){
                _showDataLoader.value = false
            }
        }
    }

    fun getAllTasks() = calendarUseCases.readAllTaskUseCase()

}
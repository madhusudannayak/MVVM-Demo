package com.example.mvvmusingjetpack.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel (application: Application) : AndroidViewModel(application) {

    private  val diaryDao =  DiaryDatabase.getDatabase(application).diaryDao()

    private val repository: DiaryRepository

    val getAllData : LiveData<List<DiaryData>>

    init {
        repository = DiaryRepository(diaryDao)
        getAllData = repository.getAllData
    }
    fun insertData(diaryData: DiaryData){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertData(diaryData)
        }
    }

}
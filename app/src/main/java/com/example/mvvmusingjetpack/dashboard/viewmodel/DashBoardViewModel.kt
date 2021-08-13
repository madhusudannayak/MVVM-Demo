package com.example.mvvmusingjetpack.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashBoardViewModel(val repository: DiaryRepository) : ViewModel() {
    var  pageCount:String =""
    val openAddFragment = MutableLiveData<Boolean>()


    fun addItem(){
        openAddFragment.value = true
    }


    fun insertNote(diaryData: DiaryData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(diaryData)
    }

    fun updateData(diaryData: DiaryData) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateData(diaryData)
    }

    private val itmsUnsync = MutableLiveData<List<DiaryData>>()

    fun getAllNotesUnsynced(): LiveData<List<DiaryData>> {
        val dataList = ArrayList<DiaryData>()
        viewModelScope.launch(Dispatchers.IO) {
            dataList.addAll(repository.getDataListBySyncStatus(0))
            itmsUnsync.postValue(dataList)
        }

        return itmsUnsync
    }

    fun deleteNode(diaryData: DiaryData) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(diaryData)
    }

    val allNotes: LiveData<List<DiaryData>> = repository.allNote








}
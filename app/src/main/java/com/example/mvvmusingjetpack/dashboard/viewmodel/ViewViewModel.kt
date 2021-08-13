package com.example.mvvmusingjetpack.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository

class ViewViewModel(val repository: DiaryRepository) : ViewModel()  {

    val nextItem = MutableLiveData<Boolean>()
    val backToDashBoardFragment = MutableLiveData<Boolean>()
    val editNote = MutableLiveData<Boolean>()
    val openSettingFragment = MutableLiveData<Boolean>()
    val openSearchFragment = MutableLiveData<Boolean>()

    var currentID: Int = 0
    var totalPage: Int = 0


    fun nextItem() {
        if (currentID.equals(totalPage - 1)) {
            nextItem.value = false
        } else {
            nextItem.value = true
            currentID++
        }
    }

    fun editNote() {
        editNote.value = true
    }

    fun searchNote() {
        openSearchFragment.value = true

    }

    fun setting() {
        openSettingFragment.value = true

    }


    fun backToDashBoard() {
        backToDashBoardFragment.value = true
    }


    fun nextItemId(): Int = currentID

 //   val repository: DiaryRepository = DiaryRepository.getInstance((application.baseContext))


    val allNotes: LiveData<List<DiaryData>> = repository.allNote



}
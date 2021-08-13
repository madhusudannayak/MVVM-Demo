package com.example.mvvmusingjetpack.add.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel(val repository: DiaryRepository) : ViewModel() {
    val backToViewFragment = MutableLiveData<Boolean>()

    fun backToView(){
        backToViewFragment.value = true
    }
  //  private val repository: DiaryRepository = DiaryRepository.getInstance((application.baseContext))

    fun updateData(diaryData: DiaryData) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateData(diaryData)
    }


}
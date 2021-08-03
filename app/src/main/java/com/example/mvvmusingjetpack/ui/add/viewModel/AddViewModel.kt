package com.example.mvvmusingjetpack.ui.add.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {
    val closeFragment = MutableLiveData<Boolean>()
    val changeIcon = MutableLiveData<Boolean>()
    var id = 1;

    fun close() {
        closeFragment.value = true
    }

    fun edit() {
        if (id == 1) {
            changeIcon.value = true
            id = 0

        } else if (id == 0) {
            changeIcon.value = false
            id = 1
        }
    }

    private val repository: DiaryRepository = DiaryRepository.getInstance((application.baseContext))

    fun insertNote(diaryData: DiaryData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(diaryData)
    }


}
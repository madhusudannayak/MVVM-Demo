package com.example.mvvmusingjetpack.view.fragments.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    val backToViewFragment = MutableLiveData<Boolean>()

    fun backToViewFragment() {
        backToViewFragment.value = true

    }

    private val repository: DiaryRepository = DiaryRepository.getInstance((application.baseContext))


    fun searchNote(searchQuery: String): LiveData<List<DiaryData>> {
        return repository.searchNote(searchQuery)
    }
}
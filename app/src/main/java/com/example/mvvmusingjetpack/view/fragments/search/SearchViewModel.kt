package com.example.mvvmusingjetpack.view.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val backToViewFragment = MutableLiveData<Boolean>()

    fun backToViewFragment(){
        backToViewFragment.value =true

    }
}
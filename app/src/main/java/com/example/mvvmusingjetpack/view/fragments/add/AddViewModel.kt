package com.example.mvvmusingjetpack.view.fragments.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {
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
    fun insertDataToRom(){

    }
}
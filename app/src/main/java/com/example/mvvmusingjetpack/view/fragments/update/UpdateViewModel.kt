package com.example.mvvmusingjetpack.view.fragments.update

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpdateViewModel : ViewModel() {
    val BackToViewFragment = MutableLiveData<Boolean>()

    fun BackToView(){
        BackToViewFragment.value = true
    }



}
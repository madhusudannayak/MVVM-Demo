package com.example.mvvmusingjetpack.fragments.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewViewModel: ViewModel() {
    val NextItem = MutableLiveData<Boolean>()
    val BackToDashBoardFragment = MutableLiveData<Boolean>()
    val EditNote = MutableLiveData<Boolean>()


    fun NextItem()
    {
        NextItem.value = true
    }


    fun EditNote(){
        EditNote.value = true
    }



    fun BackToDashBoard(){
        BackToDashBoardFragment.value = true
    }


}
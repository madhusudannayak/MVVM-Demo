package com.example.mvvmusingjetpack.view.fragments.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewViewModel : ViewModel() {

    val NextItem = MutableLiveData<Boolean>()
    val BackToDashBoardFragment = MutableLiveData<Boolean>()
    val EditNote = MutableLiveData<Boolean>()
    val openSettingFragment = MutableLiveData<Boolean>()
    val openSearchFragment = MutableLiveData<Boolean>()

    var currentID: Int = 0
    var totalPage: Int = 0


    fun NextItem() {
        if (currentID.equals(totalPage - 1)) {
            NextItem.value = false
        } else {
            NextItem.value = true
            currentID++
        }
    }

    fun EditNote() {
        EditNote.value = true
    }

    fun searchNote() {
        openSettingFragment.value = true
    }

    fun setting() {
        openSearchFragment.value = true
    }


    fun BackToDashBoard() {
        BackToDashBoardFragment.value = true
    }


    fun nextItemId(): Int = currentID


}
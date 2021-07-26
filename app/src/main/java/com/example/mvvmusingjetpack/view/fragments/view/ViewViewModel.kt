package com.example.mvvmusingjetpack.view.fragments.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewViewModel : ViewModel() {

    val NextItem = MutableLiveData<Boolean>()
    val BackToDashBoardFragment = MutableLiveData<Boolean>()
    val EditNote = MutableLiveData<Boolean>()
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


    fun BackToDashBoard() {
        BackToDashBoardFragment.value = true
    }
//    fun nextNote(){
//        viewText.text = Note[Position].text
//        UpdatePosition = Note[Position].id
//        UpdateNote = Note[Position].text
//        SetbackgroundColor(Note[Position].color.toString())
//    }
    fun nextItemId(): Int = currentID


}
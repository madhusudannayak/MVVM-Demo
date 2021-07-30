package com.example.mvvmusingjetpack.view.fragments.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashBoardViewModel  : ViewModel()  {
    var  PageCount:String =""
    val OpenAddFragment = MutableLiveData<Boolean>()


    fun AddItem(){
        OpenAddFragment.value = true
    }






}
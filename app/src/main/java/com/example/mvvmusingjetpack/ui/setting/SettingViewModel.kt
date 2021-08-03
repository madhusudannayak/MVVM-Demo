package com.example.mvvmusingjetpack.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {
    val backToDashboardFragment = MutableLiveData<Boolean>()


    fun backToDashBoardFragment() {
        backToDashboardFragment.value = true
    }
}
package com.example.mvvmusingjetpack.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {

    val signUp = MutableLiveData<Boolean>()
    val openLoginFragment = MutableLiveData<Boolean>()

    fun openLoginPage() {
        openLoginFragment.value = true

    }

    fun signup() {
        signUp.value = true
    }
}
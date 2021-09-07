package com.example.mvvmusingjetpack.auth.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val emailId = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val login = MutableLiveData<Boolean>()
    val loadingAnim = MutableLiveData<Boolean>()
    val openSignupFragment = MutableLiveData<Boolean>()


    fun openSignUpPage() {
        openSignupFragment.value = true
//        Log.d("1111111111",emailId.value.toString())
//        Log.d("1111111111",password.value.toString())


    }

    fun login() {
        login.value = true
    }


    fun loadAnimation() {
        loadingAnim.value = true
    }
}
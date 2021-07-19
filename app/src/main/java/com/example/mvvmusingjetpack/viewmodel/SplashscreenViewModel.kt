package com.example.mvvmusingjetpack.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmusingjetpack.view.MainActivity
import android.os.Handler

class SplashscreenViewModel : ViewModel() {

    val openLoginActivity = MutableLiveData<Boolean>()


    fun OpenLoginScreen()
   {
       Handler().postDelayed({
           openLoginActivity.postValue(true)
       }, 3000L)
   }

    fun doNavigateToLogin(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

}
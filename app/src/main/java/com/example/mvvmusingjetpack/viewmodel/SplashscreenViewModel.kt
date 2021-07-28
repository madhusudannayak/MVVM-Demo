package com.example.mvvmusingjetpack.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmusingjetpack.view.MainActivity
import android.os.Handler
import com.example.mvvmusingjetpack.view.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class SplashscreenViewModel : ViewModel() {

    val openLoginActivity = MutableLiveData<Boolean>()
    val openHomeActivity = MutableLiveData<Boolean>()
    val user = FirebaseAuth.getInstance().currentUser

    fun OpenLoginScreen() {
        Handler().postDelayed({
            if (user != null) {
                openHomeActivity.postValue(true)
            } else {
                openLoginActivity.postValue(true)
            }
        }, 3000L)
    }

    fun doNavigateToLogin(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    fun doNavigateToHome(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }

}
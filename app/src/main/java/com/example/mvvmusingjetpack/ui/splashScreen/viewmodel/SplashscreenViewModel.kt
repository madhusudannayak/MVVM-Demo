package com.example.mvvmusingjetpack.ui.splashScreen.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmusingjetpack.ui.MainActivity
import android.os.Handler
import android.util.Log
import com.example.mvvmusingjetpack.ui.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class SplashscreenViewModel : ViewModel() {

    val openLoginActivity = MutableLiveData<Boolean>()
    val openHomeActivity = MutableLiveData<Boolean>()
    val user = FirebaseAuth.getInstance().currentUser

    fun OpenLoginScreen() {
        Handler().postDelayed({
            if (user != null) {
                openHomeActivity.postValue(true)
                Log.d("isLogin","true")

            } else {
                Log.d("isLogin","false")
                openHomeActivity.postValue(false)


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
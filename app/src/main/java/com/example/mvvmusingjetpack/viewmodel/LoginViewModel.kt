package com.example.mvvmusingjetpack.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmusingjetpack.model.LoginData
import com.example.mvvmusingjetpack.view.HomeActivity

class LoginViewModel : ViewModel() {
    var  user:String =""
    var  password:String =""
    val loginLiveData = MutableLiveData<String>()
    val openHomeActivity = MutableLiveData<Boolean>()



    fun onLogin(){
        val valid = LoginData(user,password).isValid().toString()
        if (valid.equals("true",false)){
            openHomeActivity.value = true
        }else{
            loginLiveData.value = "Please enter Valid Details"
        }
       // isvalide.value = LoginData(user,password).isValid()
       // loginLiveData.value = LoginData(user,password).isValid().toString()

    }
    fun doNavigateToHome(context: Context) {
        context.startActivity(Intent(context,HomeActivity::class.java))
    }
}
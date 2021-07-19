package com.example.mvvmusingjetpack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.ActivityMainBinding
import com.example.mvvmusingjetpack.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    private val loginViewModel : LoginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.loginviewModel =loginViewModel
        binding.lifecycleOwner = this

        onActionPerform()
    }

    private fun onActionPerform(){
        loginViewModel.openHomeActivity.observe(this,{
            loginViewModel.doNavigateToHome(this)
        })
    }

}
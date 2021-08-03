package com.example.mvvmusingjetpack.ui.splashScreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.ActivitySplashscreenBinding
import com.example.mvvmusingjetpack.ui.splashScreen.viewmodel.SplashscreenViewModel

class SplashscreenActivity : AppCompatActivity() {
    private val splashscreenViewModel: SplashscreenViewModel by lazy {
        ViewModelProvider(this).get(
                SplashscreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySplashscreenBinding>(this,
                R.layout.activity_splashscreen
        )

        binding.splashviewModel = splashscreenViewModel
        binding.lifecycleOwner = this
        splashscreenViewModel.OpenLoginScreen()
        onActionPerform()
    }

    private fun onActionPerform() {

        splashscreenViewModel.openLoginActivity.observe(this, {
//            if (it){
//                Log.d("isLogin","true")
//            }else{
//                Log.d("isLogin","false")
//
//            }

            if (it) {
                splashscreenViewModel.doNavigateToLogin(this)
                finish()
            }


        })
        splashscreenViewModel.openHomeActivity.observe(this, {
            if (it) {
                splashscreenViewModel.doNavigateToHome(this)
                finish()
            }


        })
    }
}
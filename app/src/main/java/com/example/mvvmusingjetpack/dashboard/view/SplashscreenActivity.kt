package com.example.mvvmusingjetpack.dashboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.databinding.ActivitySplashscreenBinding
import com.example.mvvmusingjetpack.dashboard.viewmodel.SplashscreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashscreenActivity : AppCompatActivity() {

    private val splashscreenViewModel: SplashscreenViewModel by viewModel()

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
package com.example.mvvmusingjetpack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmusingjetpack.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

//    override fun passDataCom(editTextInput: String) {
//        val bundle = Bundle()
//        bundle.putString("input_txt", editTextInput)
//
//        val transaction = this.supportFragmentManager.beginTransaction()
//        val frag2 = ViewFragment()
//        frag2.arguments = bundle
//
//        transaction.replace(R.id.content_id, frag2)
//        transaction.addToBackStack(null)
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction.commit()
//    }
}
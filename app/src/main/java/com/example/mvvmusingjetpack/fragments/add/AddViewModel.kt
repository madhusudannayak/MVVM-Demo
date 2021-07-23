package com.example.mvvmusingjetpack.fragments.add

import android.util.Log
import android.widget.Switch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {
    val closeFragment = MutableLiveData<Boolean>()
    val ChangeIcon = MutableLiveData<Boolean>()
    var id = 1;

    fun Close()
    {
        closeFragment.value = true
    }

    fun Edit()
    {
        if(id.equals(1)){

            ChangeIcon.value = true
            Log.d("aaaaaaaaaaaaaaaaaaaa","true")
            id = 0

        }else if(id.equals(0)){

            ChangeIcon.value = false
            Log.d("aaaaaaaaaaaaaaaaaaaa","false")
            id = 1

        }
     }
}
package com.example.mvvmusingjetpack.fragments.dashboard

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmusingjetpack.IDiaryRVAdapter
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.db.DiaryData
import com.example.mvvmusingjetpack.db.DiaryRepository
import com.example.mvvmusingjetpack.fragments.add.addFragment
import com.example.mvvmusingjetpack.view.HomeActivity
import com.example.mvvmusingjetpack.viewmodel.DiaryViewModel

class DashBoardViewModel  : ViewModel()  {
    var  PageCount:String =""
    val OpenAddFragment = MutableLiveData<Boolean>()


    fun AddItem(){
        OpenAddFragment.value = true
    }





}
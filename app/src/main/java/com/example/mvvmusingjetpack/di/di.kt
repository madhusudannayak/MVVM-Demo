package com.example.mvvmusingjetpack.di

import com.example.mvvmusingjetpack.db.DiaryDatabase
import com.example.mvvmusingjetpack.db.DiaryRepository
import com.example.mvvmusingjetpack.ui.dashboard.viewModel.DashBoardViewModel
import com.example.mvvmusingjetpack.ui.setting.SettingViewModel
import com.example.mvvmusingjetpack.ui.view.ViewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {



  single { DiaryRepository(get()) }


  viewModel {  SettingViewModel() }

  viewModel {  DashBoardViewModel(get()) }


}
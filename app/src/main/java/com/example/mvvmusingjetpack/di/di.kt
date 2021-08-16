package com.example.mvvmusingjetpack.di

import android.app.Application
import androidx.room.Room
import com.example.mvvmusingjetpack.add.viewmodel.AddViewModel
import com.example.mvvmusingjetpack.auth.viewmodel.LoginViewModel
import com.example.mvvmusingjetpack.auth.viewmodel.SignupViewModel
import com.example.mvvmusingjetpack.db.DiaryDao
import com.example.mvvmusingjetpack.db.DiaryDatabase
import com.example.mvvmusingjetpack.db.DiaryRepository
import com.example.mvvmusingjetpack.dashboard.viewmodel.DashBoardViewModel
import com.example.mvvmusingjetpack.dashboard.viewmodel.SearchViewModel
import com.example.mvvmusingjetpack.dashboard.viewmodel.SettingViewModel
import com.example.mvvmusingjetpack.dashboard.viewmodel.SplashscreenViewModel
import com.example.mvvmusingjetpack.add.viewmodel.UpdateViewModel
import com.example.mvvmusingjetpack.dashboard.viewmodel.ViewViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    fun provideDatabase(application: Application): DiaryDatabase {
        return Room.databaseBuilder(application, DiaryDatabase::class.java, "Diary_table")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    fun provideDao(diaryDatabase: DiaryDatabase): DiaryDao {
        return diaryDatabase.getDiaryDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }

}
val repositoryModule = module {
    fun provideUserRepository(dao: DiaryDao): DiaryRepository {
        return DiaryRepository(dao)
    }
    single { provideUserRepository(get()) }
}

val viewModelModule = module {
    viewModel { DashBoardViewModel(get()) }
    viewModel { AddViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { UpdateViewModel(get()) }
    viewModel { ViewViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { SignupViewModel() }
    viewModel { SplashscreenViewModel() }
    viewModel { SettingViewModel() }


}
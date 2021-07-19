package com.example.mvvmusingjetpack.db

import androidx.lifecycle.LiveData

class DiaryRepository(private val diaryDao: DiaryDao) {

    val getAllData: LiveData<List<DiaryData>> = diaryDao.getAllData()

    suspend fun insertData(diaryData: DiaryData){
        diaryDao.insertData(diaryData)
    }

}
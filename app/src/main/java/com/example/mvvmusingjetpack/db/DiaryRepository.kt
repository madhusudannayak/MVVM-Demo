package com.example.mvvmusingjetpack.db

import androidx.lifecycle.LiveData


class DiaryRepository(private val diaryDao: DiaryDao) {

    var id:String = "1"

    val allNote: LiveData<List<DiaryData>> = diaryDao.getAllNotes()

    val allNoteById: LiveData<List<DiaryData>> = diaryDao.getNotesByID(id)


    suspend fun insert(diaryData: DiaryData){
        diaryDao.insert(diaryData)
    }
    suspend fun delete(diaryData: DiaryData){
        diaryDao.delete(diaryData)
    }

}
package com.example.mvvmusingjetpack.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room


class DiaryRepository( context: Context) {
    val diaryDao = DiaryDatabase.getDatabase(context).getDiaryDao()


    companion object{
        var instance:DiaryRepository?=null

        var getInstance : (Context)-> DiaryRepository = {
            if(instance!=null){
                instance!!
            }else{
                instance = DiaryRepository(it)
                instance!!
            }
        }
    }

    val allNote: LiveData<List<DiaryData>> = diaryDao.getAllNotes()

    val allNoteById: (String) -> LiveData<List<DiaryData>> = {diaryDao.getNotesByID(it)}


    suspend fun insert(diaryData: DiaryData){
        diaryDao.insert(diaryData)



    }
    suspend fun delete(diaryData: DiaryData){
        diaryDao.delete(diaryData)
    }
    suspend fun updateData(diaryData: DiaryData){
        diaryDao.updateData(diaryData)
        Log.d("kkkkkkkkkkkkk1","sfdsdf")
    }

}
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
//    companion object {
//        @Volatile
//        private var INSTANCE: DiaryRepository? = null
//
//        fun getDiaryRepo(context: Context): DiaryRepository {
//            return INSTANCE ?: synchronized(this) {
//                val diaryDao: DiaryDao
//                val instance = DiaryRepository(context)
//                INSTANCE = instance
//                instance
//            }
//        }
//    }


    val allNote: LiveData<List<DiaryData>> = diaryDao.getAllNotes()

    val allNoteById: (String) -> LiveData<List<DiaryData>> = {diaryDao.getNotesByID(it)}


    suspend fun insert(diaryData: DiaryData){
        diaryDao.insert(diaryData)

        Log.d("kkkkkkkkkkkkk1","sfdsdf")

    }
    suspend fun delete(diaryData: DiaryData){
        diaryDao.delete(diaryData)
    }

}
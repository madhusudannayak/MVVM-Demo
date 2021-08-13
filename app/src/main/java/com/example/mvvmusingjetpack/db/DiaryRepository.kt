package com.example.mvvmusingjetpack.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData


class DiaryRepository(private val diaryDao: DiaryDao) {

//    private val diaryDao = DiaryDatabase.getDatabase(context).getDiaryDao()
//
//    companion object {
//        var instance: DiaryRepository? = null
//
//        var getInstance: (Context) -> DiaryRepository =
//
//                {
//                    if (instance != null) {
//                        instance!!
//                    } else {
//                        instance = DiaryRepository(it)
//                        instance!!
//                    }
//                }
//    }


    val allNote: LiveData<List<DiaryData>> = diaryDao.getAllNotes()


    suspend fun insert(diaryData: DiaryData) {
        diaryDao.insert(diaryData)
    }

    suspend fun delete(diaryData: DiaryData) {
        diaryDao.delete(diaryData)
    }

    suspend fun updateData(diaryData: DiaryData) {
        diaryDao.updateData(diaryData)
    }

    suspend fun getDataListBySyncStatus(isSync: Int): List<DiaryData> {
        return diaryDao.getDataListBySyncStatus(isSync)
    }

    fun searchNote(SearchQuery: String): LiveData<List<DiaryData>> {
        return diaryDao.searchNote(SearchQuery)

    }


}
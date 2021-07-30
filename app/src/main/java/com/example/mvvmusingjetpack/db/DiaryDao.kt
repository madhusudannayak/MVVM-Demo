package com.example.mvvmusingjetpack.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(diaryData: DiaryData)

    @Delete
    suspend fun delete(diaryData: DiaryData)

    @Query("Select * from Diary_table order by id desc")
    fun getAllNotes(): LiveData<List<DiaryData>>

    @Update
    suspend fun updateData(diaryData: DiaryData)

    @Query("Select * from Diary_table WHERE isSync=:isSync ")
    suspend fun getDataListBySyncStatus(isSync: Int): List<DiaryData>

    @Query("Select * from Diary_table WHERE text LIKE :SearchQuery")
    fun searchNote(SearchQuery: String): LiveData<List<DiaryData>>



}
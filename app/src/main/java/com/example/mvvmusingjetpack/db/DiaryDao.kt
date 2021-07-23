package com.example.mvvmusingjetpack.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {

//    @Query("SELECT * FROM Diary_table ORDER BY id ASC")
//    fun getAllData(): LiveData<List<DiaryData>>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertData(diaryData: DiaryData)



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(diaryData: DiaryData)

    @Delete
    suspend fun delete(diaryData: DiaryData)

    @Query("Select * from Diary_table order by id desc")

    fun getAllNotes() : LiveData<List<DiaryData>>

    @Query("Select * from Diary_table WHERE id=:id ")
    fun getNotesByID(id: String) : LiveData<List<DiaryData>>

    @Update
    suspend fun updateData(diaryData: DiaryData)

}
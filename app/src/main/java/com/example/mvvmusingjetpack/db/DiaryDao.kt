package com.example.mvvmusingjetpack.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiaryDao {

    @Query("SELECT * FROM Diary_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<DiaryData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(diaryData: DiaryData)


}
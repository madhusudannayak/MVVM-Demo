package com.example.mvvmusingjetpack.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Diary_table")
//data class DiaryData(@PrimaryKey(autoGenerate = true)
//                     var id: Int,
//                     var note:String)

data class DiaryData(
        @PrimaryKey(autoGenerate = true) val id:Int,
        val text:String,
        val color: Color
)

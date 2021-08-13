package com.example.mvvmusingjetpack.db

import android.content.Context
import androidx.room.*

@Database(entities = arrayOf(DiaryData::class), version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class DiaryDatabase : RoomDatabase() {

    abstract fun getDiaryDao(): DiaryDao

//    companion object {
//        @Volatile
//        private var INSTANCE: DiaryDatabase? = null
//
//        fun getDatabase(context: Context): DiaryDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        DiaryDatabase::class.java,
//                        "Diary_table"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }



}


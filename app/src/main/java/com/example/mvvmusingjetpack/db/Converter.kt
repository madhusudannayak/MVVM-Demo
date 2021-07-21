package com.example.mvvmusingjetpack.db

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromPriority(color: Color):String{
        return color.name

    }
    @TypeConverter
    fun toPriority(color: String): Color{
        return Color.valueOf(color)
    }
}
package com.example.youtubeclone.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.youtubeclone.db.typeConvertors.VideosTypeConvertor

@Entity(tableName = "Users")
data class User(
    @PrimaryKey
    val id:  String,
    val username : String,
    val fullname : String,
    val email : String,
    val avatar : String = "",
    val coverimg : String = "",
    val password : String,
    val refreshToken : String,
    @TypeConverters(Videos::class)
    val watchHistory : ArrayList<Videos> = ArrayList()
)
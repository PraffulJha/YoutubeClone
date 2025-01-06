package com.example.youtubeclone.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.youtubeclone.db.typeConvertors.UserTypeConverter

@Entity(tableName = "Videos")
data class Videos(
    @PrimaryKey
    val id : String,
    val videoFile : String,
    val thumbnail : String,
    val title : String,
    val duration: Long,
    val views : Long = 0L,
    val isPublished : Boolean = true,
    @TypeConverters(UserTypeConverter::class)
    val owner : User
)
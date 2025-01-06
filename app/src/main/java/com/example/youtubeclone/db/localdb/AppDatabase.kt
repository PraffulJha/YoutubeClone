package com.example.youtubeclone.db.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.youtubeclone.db.dao.DbQueryDao
import com.example.youtubeclone.db.entity.User
import com.example.youtubeclone.db.entity.Videos
import com.example.youtubeclone.db.typeConvertors.UserTypeConverter
import com.example.youtubeclone.db.typeConvertors.VideosTypeConvertor


@Database(entities = [User::class,Videos::class], version = 1, exportSchema = false)
@TypeConverters(VideosTypeConvertor::class,UserTypeConverter::class)
abstract class  AppDatabase : RoomDatabase() {
    abstract fun dbQueryDao(): DbQueryDao
}

package com.example.youtubeclone.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.youtubeclone.db.entity.User


@Dao
interface DbQueryDao {

    @Query("SELECT * FROM Users WHERE id = :id")
    suspend fun getCurrentUser(id: String) : User

    @Query("SELECT * FROM Users WHERE refreshToken = :refreshToken")
    suspend fun getCurrentUserFromRefreshToken(refreshToken : String) : User

    @Insert
    suspend fun insertUserData(UserData : User)
}
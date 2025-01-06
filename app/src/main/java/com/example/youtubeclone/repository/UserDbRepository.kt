package com.example.youtubeclone.repository

import com.example.youtubeclone.db.dao.DbQueryDao
import com.example.youtubeclone.db.entity.User
import javax.inject.Inject

class UserDbRepository @Inject constructor(private val dbQueryDao: DbQueryDao) {

    suspend fun insertUser(userdata : User){
        dbQueryDao.insertUserData(userdata)
    }
    suspend fun getUserFromId(id : String) : User{
        return dbQueryDao.getCurrentUser(id)
    }
    suspend fun getUSerFromRefreshToken(refreshToken : String) : User{
        return dbQueryDao.getCurrentUserFromRefreshToken(refreshToken)
    }
}
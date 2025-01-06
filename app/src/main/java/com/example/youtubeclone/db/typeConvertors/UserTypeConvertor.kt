package com.example.youtubeclone.db.typeConvertors

import androidx.room.TypeConverter
import com.example.youtubeclone.db.entity.User
import com.google.gson.Gson

class UserTypeConverter {

    @TypeConverter
    fun fromUser(user: User?): String? {
        return Gson().toJson(user)
    }

    @TypeConverter
    fun toUser(userString: String?): User? {
        return if (userString == null) null else Gson().fromJson(userString, User::class.java)
    }
}
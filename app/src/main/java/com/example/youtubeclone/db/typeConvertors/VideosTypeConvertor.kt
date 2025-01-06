package com.example.youtubeclone.db.typeConvertors

import androidx.room.TypeConverter;
import com.example.youtubeclone.db.entity.Videos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


class VideosTypeConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromVideosList(videos: ArrayList<Videos?>?): String? {
        if (videos == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(videos)
    }

    @TypeConverter
    fun toVideosList(videosString: String?): ArrayList<Videos>? {
        if (videosString == null) {
            return null
        }
        val gson = Gson()
        val listType: Type? = object : TypeToken<ArrayList<Videos?>?>() {}.type
        return gson.fromJson(videosString, listType)
    }
}

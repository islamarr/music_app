package com.islam.music.features.album_details.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.islam.music.features.album_details.domain.entites.Track
import java.util.*

class Converters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromStringToList(data: String?): List<Track> {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<Track>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromListToString(myObjects: List<Track>): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }


    }
}
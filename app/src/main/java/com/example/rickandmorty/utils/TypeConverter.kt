package com.example.rickandmorty.utils

import androidx.room.TypeConverter
import com.example.rickandmorty.models.ShortLocation
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.*


class TypeConverter {

        var gson = Gson()

        @TypeConverter
        fun jsonToStringList(data: String?): List<String> {
            if (data == null) {
                return Collections.emptyList()
            }

            val type = object : TypeToken<List<String>>(){}.type
            return gson.fromJson(data, type)
        }

        @TypeConverter
        fun stringListToJson(someObjects: List<String>): String {
            return gson.toJson(someObjects)
        }

        @TypeConverter
        fun jsonToShortLoaction(data: String?): ShortLocation {
            if (data == null) {
                return ShortLocation("","")
            }

            val type = object : TypeToken<ShortLocation>(){}.type
            return gson.fromJson(data, type)
        }

        @TypeConverter
        fun shortLoactionToJson(someObjects: ShortLocation): String {
            return gson.toJson(someObjects)
        }


}
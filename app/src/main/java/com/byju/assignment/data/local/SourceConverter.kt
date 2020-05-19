package com.byju.assignment.data.local

import androidx.room.TypeConverter
import com.byju.assignment.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SourceConverter {
    @TypeConverter
    fun fromFavouriteToJson(favourites: Source): String? {
        if (favourites == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Source>() {}.type
        return gson.toJson(favourites, type)
    }

    @TypeConverter
    fun jsonToFavourites(json: String): Source? {
        if (json == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(json, type)
    }
}
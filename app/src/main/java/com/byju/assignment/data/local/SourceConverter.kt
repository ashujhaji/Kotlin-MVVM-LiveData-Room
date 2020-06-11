package com.byju.assignment.data.local

/*MIT License

        Copyright (c) 2020 Ashutosh Jha

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.*/


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
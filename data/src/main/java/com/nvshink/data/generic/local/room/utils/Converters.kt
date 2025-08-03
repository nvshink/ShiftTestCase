package com.nvshink.data.generic.local.room.utils

import androidx.room.TypeConverter
import com.nvshink.data.user.local.entity.UserEntity
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromJsonToUserEntity(string: String): UserEntity {
        return Json.decodeFromString<UserEntity>(string)
    }

    @TypeConverter
    fun fromUserEntityToJson(locationEntity: UserEntity): String {
        return Json.encodeToString(locationEntity)
    }

    @TypeConverter
    fun fromJsonToListStrings(string: String): List<String> {
        return Json.decodeFromString<List<String>>(string)
    }

    @TypeConverter
    fun fromListStringsToJson(list: List<String>): String {
        return Json.encodeToString(list)
    }
}
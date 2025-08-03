package com.nvshink.data.generic.local.room.utils

import androidx.room.TypeConverter
import com.nvshink.data.user.local.entity.location.LocationEntity
import com.nvshink.data.user.local.entity.name.NameEntity
import com.nvshink.data.user.local.entity.picture.UserPictureEntity
import kotlinx.serialization.json.Json

class Converters {

    //NameEntity
    @TypeConverter
    fun fromJsonToNameEntity(string: String): NameEntity {
        return Json.decodeFromString<NameEntity>(string)
    }

    @TypeConverter
    fun fromNameEntityToJson(nameEntity: NameEntity): String {
        return Json.encodeToString(nameEntity)
    }

    //LocationEntity
    @TypeConverter
    fun fromJsonToLocationEntity(string: String): LocationEntity {
        return Json.decodeFromString<LocationEntity>(string)
    }

    @TypeConverter
    fun fromLocationEntityToJson(locationEntity: LocationEntity): String {
        return Json.encodeToString(locationEntity)
    }

    //UserPictureEntity
    @TypeConverter
    fun fromJsonToUserPictureEntity(string: String): UserPictureEntity {
        return Json.decodeFromString<UserPictureEntity>(string)
    }

    @TypeConverter
    fun fromUserPictureEntityToJson(userPictureEntity: UserPictureEntity): String {
        return Json.encodeToString(userPictureEntity)
    }
}
package com.nvshink.data.user.utils

import com.nvshink.data.user.local.entity.UserEntity
import com.nvshink.data.user.network.response.UserResponse
import com.nvshink.data.user.network.response.location.LocationResponse
import com.nvshink.data.user.network.response.location.coordinates.CoordinatesResponse
import com.nvshink.data.user.network.response.location.street.StreetResponse
import com.nvshink.data.user.network.response.location.usertimezone.UserTimeZoneResponse
import com.nvshink.data.user.network.response.name.NameResponse
import com.nvshink.data.user.network.response.picture.UserPictureResponse
import com.nvshink.domain.user.model.UserModel
import com.nvshink.domain.user.model.location.LocationModel
import com.nvshink.domain.user.model.location.coordinates.CoordinatesModel
import com.nvshink.domain.user.model.location.street.StreetModel
import com.nvshink.domain.user.model.location.usertimezone.UserTimeZoneModel
import com.nvshink.domain.user.model.name.NameModel
import com.nvshink.domain.user.model.picture.UserPictureModel
import com.nvshink.domain.utils.UserGender
import kotlinx.serialization.json.Json
import java.time.ZonedDateTime
import java.util.TimeZone

object UserMapper {
    fun entityToModel(entity: UserEntity): UserModel = UserModel(
        username = entity.username,
        password = entity.password,
        gender = when (entity.gender) {
            "male" -> UserGender.MALE
            "female" -> UserGender.FEMALE
            else -> UserGender.UNKNOWN
        },
        name = Json.decodeFromString(entity.name),
        location = Json.decodeFromString(entity.location),
        email = entity.email,
        dob = ZonedDateTime.parse(entity.dob),
        registered = ZonedDateTime.parse(entity.registered),
        phone = entity.phone,
        cell = entity.cell,
        picture = Json.decodeFromString(entity.picture),
        nat = entity.nat
    )

    fun modelToEntity(model: UserModel): UserEntity = UserEntity(
        username = model.username,
        password = model.password,
        gender = when (model.gender) {
            UserGender.MALE -> "male"
            UserGender.FEMALE -> "female"
            UserGender.UNKNOWN -> ""
        },
        name = Json.encodeToString(model.name),
        location = Json.encodeToString(model.location),
        email = model.email,
        dob = model.dob.toString(),
        registered = model.registered.toString(),
        phone = model.phone,
        cell = model.cell,
        picture = Json.encodeToString(model.picture),
        nat = model.nat
    )

    fun responseToModel(response: UserResponse): UserModel = UserModel(
        username = response.username,
        password = response.password,
        gender = when (response.gender) {
            "male" -> UserGender.MALE
            "female" -> UserGender.FEMALE
            else -> UserGender.UNKNOWN
        },
        name = NameModel(
            title = response.name.title,
            first = response.name.first,
            last = response.name.last
        ),
        location = LocationModel(
            street = StreetModel(
                number = response.location.street.number,
                name = response.location.street.name
            ),
            city = response.location.city,
            state = response.location.state,
            country = response.location.country,
            postcode = response.location.postcode,
            coordinates = CoordinatesModel(
                latitude = response.location.coordinates.latitude,
                longitude = response.location.coordinates.longitude
            ),
            timezone = UserTimeZoneModel(
                timeZone = TimeZone.getTimeZone(response.location.timezone.timeZone),
                description = response.location.timezone.description
            )
        ),
        email = response.email,
        dob = ZonedDateTime.parse(response.dob),
        registered = ZonedDateTime.parse(response.registered),
        phone = response.phone,
        cell = response.cell,
        picture = UserPictureModel(
            large = response.picture.large,
            medium = response.picture.medium,
            thumbnail = response.picture.thumbnail
        ),
        nat = response.nat
    )

    fun modelToResponse(model: UserModel): UserResponse = UserResponse(
        username = model.username,
        password = model.password,
        gender = when (model.gender) {
            UserGender.MALE -> "male"
            UserGender.FEMALE -> "female"
            UserGender.UNKNOWN -> ""
        },
        name = NameResponse(
            title = model.name.title,
            first = model.name.first,
            last = model.name.last
        ),
        location = LocationResponse(
            street = StreetResponse(
                number = model.location.street.number,
                name = model.location.street.name
            ),
            city = model.location.city,
            state = model.location.state,
            country = model.location.country,
            postcode = model.location.postcode,
            coordinates = CoordinatesResponse(
                latitude = model.location.coordinates.latitude,
                longitude = model.location.coordinates.longitude
            ),
            timezone = UserTimeZoneResponse(
                timeZone = model.location.timezone.timeZone.toString(),
                description = model.location.timezone.description
            )
        ),
        email = model.email,
        dob = model.dob.toString(),
        registered = model.registered.toString(),
        phone = model.phone,
        cell = model.cell,
        picture = UserPictureResponse(
            large = model.picture.large,
            medium = model.picture.medium,
            thumbnail = model.picture.thumbnail
        ),
        nat = model.nat
    )

    fun entityToResponse(entity: UserEntity): UserResponse = UserResponse(
        username = entity.username,
        password = entity.password,
        gender = entity.gender,
        name = Json.decodeFromString(entity.name),
        location = Json.decodeFromString(entity.location),
        email = entity.email,
        dob = entity.dob,
        registered = entity.registered,
        phone = entity.phone,
        cell = entity.cell,
        picture = Json.decodeFromString(entity.picture),
        nat = entity.nat
    )

    fun responseToEntity(response: UserResponse): UserEntity = UserEntity(
        username = response.username,
        password = response.password,
        gender = response.gender,
        name = Json.encodeToString(response.name),
        location = Json.encodeToString(response.location),
        email = response.email,
        dob = response.dob,
        registered = response.registered,
        phone = response.phone,
        cell = response.cell,
        picture = Json.encodeToString(response.picture),
        nat = response.nat
    )
}
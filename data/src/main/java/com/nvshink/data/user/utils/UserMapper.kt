package com.nvshink.data.user.utils

import com.nvshink.data.user.local.entity.UserEntity
import com.nvshink.data.user.network.response.UserResponse
import com.nvshink.data.user.network.response.date.DateResponse
import com.nvshink.data.user.local.entity.location.LocationEntity
import com.nvshink.data.user.local.entity.location.coordinates.CoordinatesEntity
import com.nvshink.data.user.local.entity.location.street.StreetEntity
import com.nvshink.data.user.local.entity.location.usertimezone.UserTimeZoneEntity
import com.nvshink.data.user.network.response.login.LoginResponse
import com.nvshink.data.user.local.entity.name.NameEntity
import com.nvshink.data.user.local.entity.picture.UserPictureEntity
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
import java.time.ZonedDateTime

/**
 * Mapper to convert model, entity and response together
 */
object UserMapper {
    fun entityToModel(entity: UserEntity): UserModel = UserModel(
        username = entity.username,
        password = entity.password,
        gender = when (entity.gender) {
            "male" -> UserGender.MALE
            "female" -> UserGender.FEMALE
            else -> UserGender.UNKNOWN
        },
        name = NameModel(
            title = entity.name.title,
            first = entity.name.first,
            last = entity.name.last
        ),
        location = LocationModel(
            street = StreetModel(
                number = entity.location.street.number,
                name = entity.location.street.name
            ),
            city = entity.location.city,
            state = entity.location.state,
            country = entity.location.country,
            postcode = entity.location.postcode,
            coordinates = CoordinatesModel(
                latitude = entity.location.coordinates.latitude,
                longitude = entity.location.coordinates.longitude
            ),
            timeZone = UserTimeZoneModel(
                offset = entity.location.timeZone.offset,
                description = entity.location.timeZone.description
            )
        ),
        email = entity.email,
        dob = ZonedDateTime.parse(entity.dob),
        registered = ZonedDateTime.parse(entity.registered),
        phone = entity.phone,
        cell = entity.cell,
        picture = UserPictureModel(
            large = entity.picture.large,
            medium = entity.picture.medium,
            thumbnail = entity.picture.thumbnail
        ),
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
        name = NameEntity(
            title = model.name.title,
            first = model.name.first,
            last = model.name.last
        ),
        location = LocationEntity(
            street = StreetEntity(
                number = model.location.street.number,
                name = model.location.street.name
            ),
            city = model.location.city,
            state = model.location.state,
            country = model.location.country,
            postcode = model.location.postcode,
            coordinates = CoordinatesEntity(
                latitude = model.location.coordinates.latitude,
                longitude = model.location.coordinates.longitude
            ),
            timeZone = UserTimeZoneEntity(
                offset = model.location.timeZone.offset,
                description = model.location.timeZone.description
            )
        ),
        email = model.email,
        dob = model.dob.toString(),
        registered = model.registered.toString(),
        phone = model.phone,
        cell = model.cell,
        picture = UserPictureEntity(
            large = model.picture.large,
            medium = model.picture.medium,
            thumbnail = model.picture.thumbnail
        ),
        nat = model.nat
    )

    fun responseToModel(response: UserResponse): UserModel = UserModel(
        username = response.login.username,
        password = response.login.password,
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
            timeZone = UserTimeZoneModel(
                offset = response.location.timeZone.offset,
                description = response.location.timeZone.description
            )
        ),
        email = response.email,
        dob = ZonedDateTime.parse(response.dob.date),
        registered = ZonedDateTime.parse(response.registered.date),
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
        login = LoginResponse(
            username = model.username,
            password = model.password
        ),
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
            timeZone = UserTimeZoneResponse(
                offset = model.location.timeZone.offset,
                description = model.location.timeZone.description
            )
        ),
        email = model.email,
        dob = DateResponse(
            date = model.dob.toString(),
            age = ZonedDateTime.now().year - model.dob.year
        ),
        registered = DateResponse(
            date = model.registered.toString(),
            age = ZonedDateTime.now().year - model.dob.year
        ),
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
        login = LoginResponse(
            username = entity.username,
            password = entity.password
        ),
        gender = entity.gender,
        name = NameResponse(
            title = entity.name.title,
            first = entity.name.first,
            last = entity.name.last
        ),
        location = LocationResponse(
            street = StreetResponse(
                number = entity.location.street.number,
                name = entity.location.street.name
            ),
            city = entity.location.city,
            state = entity.location.state,
            country = entity.location.country,
            postcode = entity.location.postcode,
            coordinates = CoordinatesResponse(
                latitude = entity.location.coordinates.latitude,
                longitude = entity.location.coordinates.longitude
            ),
            timeZone = UserTimeZoneResponse(
                offset = entity.location.timeZone.offset,
                description = entity.location.timeZone.description
            )
        ),
        email = entity.email,
        dob = DateResponse(
            date = entity.dob,
            age = ZonedDateTime.now().year - ZonedDateTime.parse(entity.dob).year
        ),
        registered = DateResponse(
            date = entity.registered,
            age = ZonedDateTime.now().year - ZonedDateTime.parse(entity.dob).year
        ),
        phone = entity.phone,
        cell = entity.cell,
        picture = UserPictureResponse(
            large = entity.picture.large,
            medium = entity.picture.medium,
            thumbnail = entity.picture.thumbnail
        ),
        nat = entity.nat
    )

    fun responseToEntity(response: UserResponse): UserEntity = UserEntity(
        username = response.login.username,
        password = response.login.password,
        gender = response.gender,
        name = NameEntity(
            title = response.name.title,
            first = response.name.first,
            last = response.name.last
        ),
        location = LocationEntity(
            street = StreetEntity(
                number = response.location.street.number,
                name = response.location.street.name
            ),
            city = response.location.city,
            state = response.location.state,
            country = response.location.country,
            postcode = response.location.postcode,
            coordinates = CoordinatesEntity(
                latitude = response.location.coordinates.latitude,
                longitude = response.location.coordinates.longitude
            ),
            timeZone = UserTimeZoneEntity(
                offset = response.location.timeZone.offset,
                description = response.location.timeZone.description
            )
        ),
        email = response.email,
        dob = response.dob.date,
        registered = response.registered.date,
        phone = response.phone,
        cell = response.cell,
        picture = UserPictureEntity(
            large = response.picture.large,
            medium = response.picture.medium,
            thumbnail = response.picture.thumbnail
        ),
        nat = response.nat
    )
}
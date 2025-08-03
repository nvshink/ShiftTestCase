package com.nvshink.domain.user.model

import com.nvshink.domain.user.model.location.LocationModel
import com.nvshink.domain.user.model.name.NameModel
import com.nvshink.domain.user.model.picture.UserPictureModel
import com.nvshink.domain.utils.UserGender
import java.time.ZonedDateTime

/**
 * @param name The name of the user.
 * @param gender The gender of the user ('female' or 'male').
 * @param location Name and link to the user's last known location endpoint.
 * TODO("Fill parameters")
 */
data class UserModel(
    val username: String,
    val password: String,
    val gender: UserGender,
    val name: NameModel,
    val location: LocationModel,
    val email: String,
    val dob: ZonedDateTime,
    val registered: ZonedDateTime,
    val phone: String,
    val cell: String,
    val picture: UserPictureModel,
    val nat: String
)

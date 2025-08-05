package com.nvshink.domain.user.model

import com.nvshink.domain.user.model.location.LocationModel
import com.nvshink.domain.user.model.name.NameModel
import com.nvshink.domain.user.model.picture.UserPictureModel
import com.nvshink.domain.utils.UserGender
import java.time.ZonedDateTime

/**
 * @param username The name of the user in system.
 * @param password The password of the user in system.
 * @param gender The gender of the user ('female' or 'male').
 * @param name The user's name, which consists of an address, first name, and last name.
 * @param location Full address and coordinates of user location.
 * @param email Email of user.
 * @param dob User's date of birth.
 * @param registered Date of user registration.
 * @param phone The user's landline phone number.
 * @param cell The user's cell phone number.
 * @param picture User pictures of different sizes.
 * @param nat User's nationality.
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

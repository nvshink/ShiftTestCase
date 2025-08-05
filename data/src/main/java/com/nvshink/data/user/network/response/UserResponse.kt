package com.nvshink.data.user.network.response

import com.nvshink.data.user.network.response.date.DateResponse
import com.nvshink.data.user.network.response.login.LoginResponse
import com.nvshink.data.user.network.response.location.LocationResponse
import com.nvshink.data.user.network.response.name.NameResponse
import com.nvshink.data.user.network.response.picture.UserPictureResponse
import kotlinx.serialization.Serializable

/**
 * @param gender The gender of the user ('female' or 'male').
 * @param name The user's name, which consists of an address, first name, and last name.
 * @param location Full address and coordinates of user location.
 * @param email Email of user.
 * @param login Login information.
 * @param dob User's date of birth.
 * @param registered Date of user registration.
 * @param phone The user's landline phone number.
 * @param cell The user's cell phone number.
 * @param picture User pictures of different sizes.
 * @param nat User's nationality.
 */
@Serializable
data class UserResponse (
    val gender: String,
    val name: NameResponse,
    val location: LocationResponse,
    val email: String,
    val login: LoginResponse,
    val dob: DateResponse,
    val registered: DateResponse,
    val phone: String,
    val cell: String,
    val picture: UserPictureResponse,
    val nat: String
)
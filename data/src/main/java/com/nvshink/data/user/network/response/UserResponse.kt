package com.nvshink.data.user.network.response

import com.nvshink.data.user.network.response.location.LocationResponse
import com.nvshink.data.user.network.response.name.NameResponse
import com.nvshink.data.user.network.response.picture.UserPictureResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse (
    val gender: String,
    val name: NameResponse,
    val location: LocationResponse,
    val email: String,
    val username: String,
    val password: String,
    val dob: String,
    val registered: String,
    val phone: String,
    val cell: String,
    val picture: UserPictureResponse,
    val nat: String
)
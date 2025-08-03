package com.nvshink.data.user.network.response

import com.nvshink.data.user.network.response.date.DateResponse
import com.nvshink.data.user.local.entity.location.LocationEntity
import com.nvshink.data.user.network.response.login.LoginResponse
import com.nvshink.data.user.local.entity.name.NameEntity
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
    val login: LoginResponse,
    val dob: DateResponse,
    val registered: DateResponse,
    val phone: String,
    val cell: String,
    val picture: UserPictureResponse,
    val nat: String
)
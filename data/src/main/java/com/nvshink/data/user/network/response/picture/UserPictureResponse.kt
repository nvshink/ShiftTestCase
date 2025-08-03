package com.nvshink.data.user.network.response.picture

import kotlinx.serialization.Serializable

@Serializable
data class UserPictureResponse(
    val large: String,
    val medium: String,
    val thumbnail: String
)

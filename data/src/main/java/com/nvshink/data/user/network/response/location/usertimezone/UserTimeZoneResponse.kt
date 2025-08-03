package com.nvshink.data.user.network.response.location.usertimezone

import kotlinx.serialization.Serializable

@Serializable
data class UserTimeZoneResponse(
    val offset: String,
    val description: String
)

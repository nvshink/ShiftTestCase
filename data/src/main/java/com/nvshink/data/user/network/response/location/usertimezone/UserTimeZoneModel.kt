package com.nvshink.data.user.network.response.location.usertimezone

import kotlinx.serialization.Serializable
import java.util.TimeZone

@Serializable
data class UserTimeZoneResponse(
    val timeZone: String,
    val description: String
)

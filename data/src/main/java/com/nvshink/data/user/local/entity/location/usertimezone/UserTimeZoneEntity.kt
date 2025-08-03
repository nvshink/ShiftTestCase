package com.nvshink.data.user.local.entity.location.usertimezone

import kotlinx.serialization.Serializable

@Serializable
data class UserTimeZoneEntity(
    val offset: String,
    val description: String
)

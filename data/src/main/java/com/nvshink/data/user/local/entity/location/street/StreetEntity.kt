package com.nvshink.data.user.local.entity.location.street

import kotlinx.serialization.Serializable

@Serializable
data class StreetEntity(
    val number: Int,
    val name: String
)

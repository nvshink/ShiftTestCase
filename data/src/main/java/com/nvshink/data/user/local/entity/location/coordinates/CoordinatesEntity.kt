package com.nvshink.data.user.local.entity.location.coordinates

import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesEntity(
    val latitude: String,
    val longitude: String
)

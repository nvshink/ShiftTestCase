package com.nvshink.data.user.network.response.location.coordinates

import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesResponse(
    val latitude: String,
    val longitude: String
)

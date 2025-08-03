package com.nvshink.data.user.network.response.location.street

import kotlinx.serialization.Serializable

@Serializable
data class StreetResponse(
    val number: Int,
    val name: String
)

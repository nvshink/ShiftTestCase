package com.nvshink.data.user.network.response.location.street

import kotlinx.serialization.Serializable

/**
 * @param number Number of location building
 * @param name Name of street
 */
@Serializable
data class StreetResponse(
    val number: Int,
    val name: String
)

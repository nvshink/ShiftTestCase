package com.nvshink.data.user.local.entity.location.street

import kotlinx.serialization.Serializable

/**
 * @param number Number of location building
 * @param name Name of street
 */
@Serializable
data class StreetEntity(
    val number: Int,
    val name: String
)

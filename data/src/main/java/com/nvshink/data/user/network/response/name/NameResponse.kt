package com.nvshink.data.user.network.response.name

import kotlinx.serialization.Serializable

/**
 * User in real life first and second names and title
 */
@Serializable
data class NameResponse(
    val title: String,
    val first: String,
    val last: String
)

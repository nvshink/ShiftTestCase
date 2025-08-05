package com.nvshink.data.user.local.entity.name

import kotlinx.serialization.Serializable

/**
 * User in real life first and second names and title
 */
@Serializable
data class NameEntity(
    val title: String,
    val first: String,
    val last: String
)

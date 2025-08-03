package com.nvshink.data.user.local.entity.name

import kotlinx.serialization.Serializable

@Serializable
data class NameEntity(
    val title: String,
    val first: String,
    val last: String
)

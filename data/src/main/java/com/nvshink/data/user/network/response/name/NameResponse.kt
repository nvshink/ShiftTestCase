package com.nvshink.data.user.network.response.name

import kotlinx.serialization.Serializable

@Serializable
data class NameResponse(
    val title: String,
    val first: String,
    val last: String
)

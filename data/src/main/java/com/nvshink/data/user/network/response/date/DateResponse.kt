package com.nvshink.data.user.network.response.date

import kotlinx.serialization.Serializable

@Serializable
data class DateResponse(
    val date: String,
    val age: Int
)

package com.nvshink.data.user.network.response.date

import kotlinx.serialization.Serializable

/**
 * Definitions of the returned date
 * @param date Event date
 * @param age The number of years from the current date to the date of the event
 */
@Serializable
data class DateResponse(
    val date: String,
    val age: Int
)

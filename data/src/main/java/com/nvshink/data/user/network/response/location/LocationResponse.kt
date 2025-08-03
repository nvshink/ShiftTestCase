package com.nvshink.data.user.network.response.location

import com.nvshink.data.user.network.response.location.coordinates.CoordinatesResponse
import com.nvshink.data.user.network.response.location.street.StreetResponse
import com.nvshink.data.user.network.response.location.usertimezone.UserTimeZoneResponse
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val street: StreetResponse,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int,
    val coordinates: CoordinatesResponse,
    val timezone: UserTimeZoneResponse
)

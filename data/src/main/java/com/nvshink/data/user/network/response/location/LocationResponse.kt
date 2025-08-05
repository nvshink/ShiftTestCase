package com.nvshink.data.user.network.response.location

import com.nvshink.data.user.network.response.location.coordinates.CoordinatesResponse
import com.nvshink.data.user.network.response.location.street.StreetResponse
import com.nvshink.data.user.network.response.location.usertimezone.UserTimeZoneResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User location
 * @param street Location street name
 * @param city Location city name
 * @param state Location state name
 * @param country Location county name
 * @param postcode User postcode
 * @param coordinates User coordinates
 * @param timeZone Time zone of user location
 */
@Serializable
data class LocationResponse(
    val street: StreetResponse,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesResponse,
    @SerialName("timezone")
    val timeZone: UserTimeZoneResponse
)

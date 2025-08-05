package com.nvshink.data.user.local.entity.location

import com.nvshink.data.user.local.entity.location.coordinates.CoordinatesEntity
import com.nvshink.data.user.local.entity.location.street.StreetEntity
import com.nvshink.data.user.local.entity.location.usertimezone.UserTimeZoneEntity
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
data class LocationEntity(
    val street: StreetEntity,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesEntity,
    @SerialName("timezone")
    val timeZone: UserTimeZoneEntity
)

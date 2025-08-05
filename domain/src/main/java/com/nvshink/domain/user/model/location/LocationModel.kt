package com.nvshink.domain.user.model.location

import com.nvshink.domain.user.model.location.coordinates.CoordinatesModel
import com.nvshink.domain.user.model.location.street.StreetModel
import com.nvshink.domain.user.model.location.usertimezone.UserTimeZoneModel

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
data class LocationModel(
    val street: StreetModel,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: CoordinatesModel,
    val timeZone: UserTimeZoneModel
)

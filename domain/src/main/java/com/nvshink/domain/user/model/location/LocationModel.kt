package com.nvshink.domain.user.model.location

import com.nvshink.domain.user.model.location.coordinates.CoordinatesModel
import com.nvshink.domain.user.model.location.street.StreetModel
import com.nvshink.domain.user.model.location.usertimezone.UserTimeZoneModel

data class LocationModel(
    val street: StreetModel,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int,
    val coordinates: CoordinatesModel,
    val timezone: UserTimeZoneModel
)

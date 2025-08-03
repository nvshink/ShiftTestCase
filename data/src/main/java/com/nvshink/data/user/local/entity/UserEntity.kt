package com.nvshink.data.user.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nvshink.domain.user.model.location.LocationModel
import com.nvshink.domain.user.model.name.NameModel
import com.nvshink.domain.user.model.picture.UserPictureModel
import com.nvshink.domain.utils.UserGender
import java.time.ZonedDateTime

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val username: String,
    val password: String,
    val gender: String,
    val name: String,
    val location: String,
    val email: String,
    val dob: String,
    val registered: String,
    val phone: String,
    val cell: String,
    val picture: String,
    val nat: String
)

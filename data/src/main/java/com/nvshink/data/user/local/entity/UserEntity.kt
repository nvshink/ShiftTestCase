package com.nvshink.data.user.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nvshink.data.user.local.entity.location.LocationEntity
import com.nvshink.data.user.local.entity.name.NameEntity
import com.nvshink.data.user.local.entity.picture.UserPictureEntity
import com.nvshink.domain.user.model.location.LocationModel
import com.nvshink.domain.user.model.name.NameModel
import com.nvshink.domain.user.model.picture.UserPictureModel
import com.nvshink.domain.utils.UserGender
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Entity(tableName = "users")
@Serializable
data class UserEntity(
    @PrimaryKey
    val username: String,
    val password: String,
    val gender: String,
    val name: NameEntity,
    val location: LocationEntity,
    val email: String,
    val dob: String,
    val registered: String,
    val phone: String,
    val cell: String,
    val picture: UserPictureEntity,
    val nat: String
)

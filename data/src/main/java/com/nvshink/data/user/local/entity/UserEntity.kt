package com.nvshink.data.user.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nvshink.data.user.local.entity.location.LocationEntity
import com.nvshink.data.user.local.entity.name.NameEntity
import com.nvshink.data.user.local.entity.picture.UserPictureEntity
import kotlinx.serialization.Serializable

/**
 * @param username The name of the user in system.
 * @param password The password of the user in system.
 * @param gender The gender of the user ('female' or 'male').
 * @param name The user's name, which consists of an address, first name, and last name.
 * @param location Full address and coordinates of user location.
 * @param email Email of user.
 * @param dob User's date of birth.
 * @param registered Date of user registration.
 * @param phone The user's landline phone number.
 * @param cell The user's cell phone number.
 * @param picture User pictures of different sizes.
 * @param nat User's nationality.
 */
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

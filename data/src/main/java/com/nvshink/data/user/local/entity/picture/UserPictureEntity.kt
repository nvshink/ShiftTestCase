package com.nvshink.data.user.local.entity.picture

import kotlinx.serialization.Serializable

@Serializable
data class UserPictureEntity(
    val large: String,
    val medium: String,
    val thumbnail: String
)

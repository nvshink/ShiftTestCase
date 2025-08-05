package com.nvshink.data.user.local.entity.picture

import kotlinx.serialization.Serializable

/**
 * Links to user pictures different sizes
 * @param large size 128px*128px
 * @param medium size 72px*72px
 * @param thumbnail size 48px*48px
 */
@Serializable
data class UserPictureEntity(
    val large: String,
    val medium: String,
    val thumbnail: String
)

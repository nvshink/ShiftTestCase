package com.nvshink.data.user.network.response.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val username: String,
    val password: String
)

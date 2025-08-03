package com.nvshink.data.generic.network.response

import kotlinx.serialization.Serializable

@Serializable
data class PageInfoResponse(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)

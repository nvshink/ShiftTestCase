package com.nvshink.data.generic.network.response

import kotlinx.serialization.Serializable

/**
 * Defines the information that will be requested and returned from the remote resource.
 * @param seed The seed used to generate users
 * @param results Number of results returned
 * @param page Data page number
 * @param version DVersion of API
 */
@Serializable
data class PageInfoResponse(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)

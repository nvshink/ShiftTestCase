package com.nvshink.data.generic.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * API returns object contains next
 * @param info Returned information about the parameters of the loaded page
 * @param results Query results
 */
@Serializable
data class PageResponse<T>(
    @SerialName("info")
    val info: PageInfoResponse,
    @SerialName("results")
    val results: List<T>
)

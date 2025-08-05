package com.nvshink.domain.pageinfo

/**
 * Defines the information that will be requested and returned from the remote resource.
 * @param pageCount Data page number
 * @param seed The seed used to generate users
 * @param results Number of results returned
 * @param version DVersion of API
 */
data class PageInfoModel(
    val pageCount: Int,
    val seed: String,
    val results: Int,
    val version: String
)
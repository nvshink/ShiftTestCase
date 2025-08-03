package com.nvshink.domain.pageinfo

data class PageInfoModel(
    val pageCount: Int,
    val seed: String,
    val results: Int,
    val version: String
)
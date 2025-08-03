package com.nvshink.domain.resource

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T, val isLocal: Boolean = false, val onlineException: Throwable? = null) : Resource<T>()
    data class Error<out T>(val exception: Throwable) : Resource<T>()
}
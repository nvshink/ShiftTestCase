package com.nvshink.data.user.network.service

import com.nvshink.data.generic.network.response.PageResponse
import com.nvshink.data.user.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/")
    suspend fun getGetListOfUsers(
        @Query("seed") seed: String,
        @Query("results") results: Int,
        @Query("page") page: Int,
    ) : PageResponse<UserResponse>
}
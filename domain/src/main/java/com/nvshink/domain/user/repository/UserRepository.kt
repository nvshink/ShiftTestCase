package com.nvshink.domain.user.repository

import com.nvshink.domain.user.model.UserModel
import com.nvshink.domain.pageinfo.PageInfoModel
import com.nvshink.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /**
     * Get list of users and page:
     * @param pageInfoModel Request page info. If data is from cache it is null.
     */
    suspend fun getUsers(
        pageInfoModel: PageInfoModel?
    ): Flow<Resource<Pair<PageInfoModel?, List<UserModel>>>>
}
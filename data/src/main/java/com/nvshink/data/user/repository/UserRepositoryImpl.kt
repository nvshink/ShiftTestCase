package com.nvshink.data.user.repository

import android.util.Log
import com.nvshink.data.generic.network.response.PageResponse
import com.nvshink.data.user.local.dao.UserDao
import com.nvshink.data.user.network.response.UserResponse
import com.nvshink.data.user.network.service.UserService
import com.nvshink.data.user.utils.UserMapper
import com.nvshink.domain.pageinfo.PageInfoModel
import com.nvshink.domain.resource.Resource
import com.nvshink.domain.user.model.UserModel
import com.nvshink.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val service: UserService
) : UserRepository {
    override suspend fun getUsers(pageInfoModel: PageInfoModel?): Flow<Resource<Pair<PageInfoModel?, List<UserModel>>>> = flow {
        emit(Resource.Loading)
        try {
            var response: PageResponse<UserResponse>
            if (pageInfoModel != null) {
                    response = service.getGetListOfUsers(
                        seed = pageInfoModel.seed,
                        results = pageInfoModel.results,
                        page = pageInfoModel.pageCount
                    )


            //Separate info and result
            val responseInfo = response.info
            val responseResult = response.results

            val newPageInfoModel = PageInfoModel(
                pageCount = responseInfo.page,
                seed = responseInfo.seed,
                results = responseInfo.results,
                version = responseInfo.version
            )

            //White result in local DB
            responseResult.forEach {
                dao.upsertUser(UserMapper.responseToEntity(it))
            }

            // Return users
            emit(
                Resource.Success(
                    Pair(
                        first = newPageInfoModel,
                        second = responseResult.map {
                            UserMapper.responseToModel(it)
                        }
                    ),
                    isLocal = false
                )
            )
                }
        } catch (e: Exception) {
            Log.d("DATA_LOAD", e.message ?: "")
            //Try load from cache
            try {
                dao.getUser().map {
                    it.map { entity ->
                        UserMapper.entityToModel(entity)
                    }
                }.collect { item ->
                    emit(
                        Resource.Success(
                            data = Pair(
                                first = null,
                                second = item
                            ),
                            isLocal = true
                        )
                    )
                }
            } catch (dbException: Throwable) {
                Log.d("DATA_LOAD", "Error ${dbException.message}")
                emit(Resource.Error(dbException))
            }
        }
    }

}
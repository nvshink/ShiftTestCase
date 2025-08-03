package com.nvshink.data.user.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nvshink.data.user.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(userEntity: UserEntity)
    @Query("SELECT * FROM users")
    fun getUser(): Flow<List<UserEntity>>

}
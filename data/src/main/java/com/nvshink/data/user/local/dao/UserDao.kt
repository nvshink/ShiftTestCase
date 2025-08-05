package com.nvshink.data.user.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nvshink.data.user.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    /**
     * Insert new or update existing user by `userEntity`
     */
    @Upsert
    suspend fun upsertUser(userEntity: UserEntity)

    /**
     * Get flow of users list from `users` table
     */
    @Query("SELECT * FROM users")
    fun getUser(): Flow<List<UserEntity>>

}
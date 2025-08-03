package com.nvshink.data.generic.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nvshink.data.generic.local.room.utils.Converters
import com.nvshink.data.user.local.dao.UserDao
import com.nvshink.data.user.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ShiftTestCaseDB : RoomDatabase() {
    abstract val userDao: UserDao
}
package com.nvshink.data.generic.local.di

import android.content.Context
import androidx.room.Room
import com.nvshink.data.generic.local.room.db.ShiftTestCaseDB
import com.nvshink.data.user.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): ShiftTestCaseDB {
        return Room.databaseBuilder(
            context,
            ShiftTestCaseDB::class.java,
            "local_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(db: ShiftTestCaseDB): UserDao = db.userDao

}
package com.nvshink.data.generic.repositories.di

import com.nvshink.data.user.repository.UserRepositoryImpl
import com.nvshink.domain.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindCharacterRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
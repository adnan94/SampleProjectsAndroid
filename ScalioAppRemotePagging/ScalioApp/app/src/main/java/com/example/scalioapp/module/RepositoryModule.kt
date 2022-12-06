package com.example.scalioapp.module

import com.example.scalioapp.source.UsersRepository
import com.example.scalioapp.source.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun userRepository(userRepositoryImpl: UsersRepositoryImpl) : UsersRepository

}
package com.example.news.di.module

import com.example.news.model.repository.MainRepository
import com.example.news.model.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: MainRepositoryImpl) : MainRepository

}
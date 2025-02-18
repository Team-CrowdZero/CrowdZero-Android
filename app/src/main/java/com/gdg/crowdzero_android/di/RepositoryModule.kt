package com.gdg.crowdzero_android.di

import com.gdg.data.repositoryimpl.CrowdZeroRepositoryImpl
import com.gdg.domain.repository.CrowdZeroRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCrowdZeroRepository(crowdZeroRepositoryImpl: CrowdZeroRepositoryImpl): CrowdZeroRepository
}

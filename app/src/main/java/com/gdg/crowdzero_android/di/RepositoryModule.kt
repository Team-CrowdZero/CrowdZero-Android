package com.gdg.crowdzero_android.di

import com.gdg.data.repositoryimpl.CrowdZeroRepositoryImpl
import com.gdg.data.repositoryimpl.ExampleRepositoryImpl
import com.gdg.domain.repository.CrowdZeroRepository
import com.gdg.domain.repository.ExampleRepository
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
    abstract fun bindExampleRepository(exampleRepositoryImpl: ExampleRepositoryImpl): ExampleRepository

    @Binds
    @Singleton
    abstract fun bindCrowdZeroRepository(crowdZeroRepositoryImpl: CrowdZeroRepositoryImpl): CrowdZeroRepository
}

package com.gdg.crowdzero_android.di

import com.gdg.data.datasource.CrowdZeroDataSource
import com.gdg.data.datasourceimpl.CrowdZeroDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCrowdZeroDataSource(crowdZeroDataSourceImpl: CrowdZeroDataSourceImpl): CrowdZeroDataSource
}

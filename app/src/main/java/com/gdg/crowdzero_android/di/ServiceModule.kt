package com.gdg.crowdzero_android.di

import com.gdg.data.service.CrowdZeroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideCrowdZeroService(
        @CrowdZeroRetrofit retrofit: Retrofit
    ): CrowdZeroService = retrofit.create(CrowdZeroService::class.java)
}

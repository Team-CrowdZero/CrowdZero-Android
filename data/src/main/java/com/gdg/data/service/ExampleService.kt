package com.gdg.data.service

import com.gdg.data.dto.ExampleBaseResponse
import com.gdg.data.dto.response.ExampleResponseDto
import com.gdg.data.service.ApiKeyStorage.API
import com.gdg.data.service.ApiKeyStorage.PAGE
import com.gdg.data.service.ApiKeyStorage.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleService {
    @GET("/$API/$USERS")
    suspend fun getUsers(
        @Query(PAGE) page: Int
    ): ExampleBaseResponse<List<ExampleResponseDto>>
}
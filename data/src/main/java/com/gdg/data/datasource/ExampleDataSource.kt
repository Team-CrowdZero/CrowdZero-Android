package com.gdg.data.datasource

import com.gdg.data.dto.ExampleBaseResponse
import com.gdg.data.dto.response.ExampleResponseDto

interface ExampleDataSource {
    suspend fun getUsers(page: Int): ExampleBaseResponse<List<ExampleResponseDto>>
}
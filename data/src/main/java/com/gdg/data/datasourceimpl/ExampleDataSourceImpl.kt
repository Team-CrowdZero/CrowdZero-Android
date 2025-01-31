package com.gdg.data.datasourceimpl

import com.gdg.data.datasource.ExampleDataSource
import com.gdg.data.dto.ExampleBaseResponse
import com.gdg.data.dto.response.ExampleResponseDto
import com.gdg.data.service.ExampleService
import javax.inject.Inject

class ExampleDataSourceImpl @Inject constructor(
    private val exampleApiService: ExampleService
) : ExampleDataSource {
    override suspend fun getUsers(page: Int): ExampleBaseResponse<List<ExampleResponseDto>> {
        return exampleApiService.getUsers(page)
    }
}

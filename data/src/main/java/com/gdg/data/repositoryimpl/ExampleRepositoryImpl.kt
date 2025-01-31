package com.gdg.data.repositoryimpl

import com.gdg.data.datasource.ExampleDataSource
import com.gdg.data.mapper.toExampleEntity
import com.gdg.domain.entity.ExampleEntity
import com.gdg.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) : ExampleRepository {
    override suspend fun getUsers(page: Int): Result<List<ExampleEntity>> {
        return runCatching {
            exampleDataSource.getUsers(page).data?.map { it.toExampleEntity() } ?: emptyList()
        }
    }
}

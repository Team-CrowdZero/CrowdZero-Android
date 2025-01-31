package com.gdg.data.mapper

import com.gdg.data.dto.response.ExampleResponseDto
import com.gdg.domain.entity.ExampleEntity

fun ExampleResponseDto.toExampleEntity() = ExampleEntity(
    id,
    email,
    firstName,
    avatar
)

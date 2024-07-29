package io.maaaae.panama_canal.dto.category

import io.maaaae.panama_canal.common.exception.NonNullableFieldException
import io.maaaae.panama_canal.domain.Category

fun Category.toResponse() = CategoryResponse(
    id = categoryId,
    name = name,
    domain = domain,
    secret = "secret",
    description = description
)

fun CategoryRequest.toCreateEntity() = Category(
    name = name ?: throw NonNullableFieldException("name 은 null 일 수 없습니다."),
    description = description,
    domain = domain ?: throw NonNullableFieldException("name 은 null 일 수 없습니다.")
)
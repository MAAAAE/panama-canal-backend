package io.maaaae.panama_canal.dto.category

import io.maaaae.panama_canal.common.exception.NonNullableFieldException
import io.maaaae.panama_canal.domain.Category

fun Category.toResponse() = CategoryResponse(
    id = categoryId,
    name = name,
    domain = domain,
    secretValue = secretValue,
    secretKey = secretKey,
    secretType = secretType,
    description = description
)

fun CategoryRequest.toCreateEntity() = Category(
    name = name ?: throw NonNullableFieldException("name 은 null 일 수 없습니다."),
    description = description,
    domain = domain ?: throw NonNullableFieldException("domain 은 null 일 수 없습니다."),
    secretValue = secretValue ?: throw NonNullableFieldException("secret 은 null 일 수 없습니다."),
    secretKey = secretKey ?: throw NonNullableFieldException("secret 은 null 일 수 없습니다."),
    secretType = secretType ?: throw NonNullableFieldException("secret 은 null 일 수 없습니다.")
)
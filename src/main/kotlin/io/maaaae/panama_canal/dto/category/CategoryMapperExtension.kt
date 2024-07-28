package io.maaaae.panama_canal.dto.category

import io.maaaae.panama_canal.domain.Category

fun Category.toResponse() = CategoryResponse(
    id = categoryId,
    name = name,
    domain = domain,
    secret = "secret",
    description = description
)

fun CategoryRequest.toCreateEntity() = Category(
    name = name,
    description = description,
    domain = domain
)
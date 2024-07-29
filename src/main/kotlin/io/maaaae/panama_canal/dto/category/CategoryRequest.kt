package io.maaaae.panama_canal.dto.category

data class CategoryRequest(
    val name: String,
    val domain: String,
    val secret: String,
)

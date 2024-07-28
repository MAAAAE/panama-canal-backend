package io.maaaae.panama_canal.dto.category

data class CategoryResponse(
    val id: Long,
    val name: String,
    val secret: String,
    val domain: String,
    val description: String?,
)

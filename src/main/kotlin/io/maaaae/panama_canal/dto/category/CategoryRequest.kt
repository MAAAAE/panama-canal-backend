package io.maaaae.panama_canal.dto.category

data class CategoryRequest(
    val name: String? = null,
    val domain: String? = null,
    val secret: String? = null,
    val description: String? = null,
)
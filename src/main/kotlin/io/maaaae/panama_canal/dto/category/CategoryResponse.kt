package io.maaaae.panama_canal.dto.category

import io.maaaae.panama_canal.domain.SecretType

data class CategoryResponse(
    val id: Long,
    val name: String,
    val secretValue: String,
    val domain: String,
    val description: String?,
    val secretKey: String,
    val secretType: SecretType,
)

data class CategoryOptionResponse(
    val id: Long,
    val label: String,
)

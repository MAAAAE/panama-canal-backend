package io.maaaae.panama_canal.dto.category

import io.maaaae.panama_canal.domain.SecretType
import jakarta.validation.constraints.NotEmpty

data class CategoryRequest(
    @field:NotEmpty(message = "이름이 없습니다.")
    val name: String? = null,
    @field:NotEmpty(message = "도메인이 없습니다.")
    val domain: String? = null,
    val secretKey: String? = null,
    val secretValue: String? = null,
    val secretType: SecretType? = null,
    val description: String? = null,
)
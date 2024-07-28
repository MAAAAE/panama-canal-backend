package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.common.constant.Method

data class SpecsDto(
    val endpoint: String,
    val method: Method,
    val categoryId: Long,
)

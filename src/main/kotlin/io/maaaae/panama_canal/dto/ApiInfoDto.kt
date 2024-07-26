package io.maaaae.panama_canal.dto

import io.maaaae.panama_canal.common.constant.Method

data class ApiInfoDto(
    val endpoint: String,
    val method: Method,
    val categoryId: Long,
)

package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.common.constant.Method

data class SpecsDto(
    val name: String,
    val endpoint: String,
    val method: Method,
    val categoryId: Long,
    val routeConfigId: Long,
)

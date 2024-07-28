package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.domain.api_info.ApiInfo

fun ApiInfo.toDto() = SpecsDto(
    endpoint = this.endpoint,
    method = this.method,
    categoryId = this.category.categoryId
)
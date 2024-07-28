package io.maaaae.panama_canal.dto

import io.maaaae.panama_canal.domain.ApiInfo

fun ApiInfo.toDto() = ApiInfoDto(
    endpoint = this.endpoint,
    method = this.method,
    categoryId = this.category.categoryId
)
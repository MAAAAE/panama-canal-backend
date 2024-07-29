package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.domain.api_info.ApiInfo

fun ApiInfo.toSpecDto() = SpecsDto(
    endpoint = this.endpoint,
    method = this.method,
    headers = this.headers,
    categoryId = this.category.categoryId
)

fun SpecsRequest.toApiInfoEntity(category: Category) = ApiInfo(
    name = this.name,
    endpoint = this.endpoint,
    method = this.method,
    headers = this.headers,
    category = category,
)

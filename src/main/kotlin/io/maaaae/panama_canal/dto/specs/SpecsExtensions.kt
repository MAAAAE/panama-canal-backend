package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.domain.api_info.ApiInfo

fun ApiInfo.toSpecDto() = SpecsDto(
    name = this.name,
    endpoint = this.endpoint,
    method = this.method,
    categoryId = this.category.categoryId,
    routeConfigId = this.routeConfig?.id ?: 0L,
)

fun SpecsRequest.toApiInfoEntity(category: Category, routeConfig: DynamicRouteConfig) = ApiInfo(
    name = this.name,
    endpoint = this.endpoint,
    method = this.method,
    request = this.request,
    response = this.response,
    category = category,
    routeConfig = routeConfig,
)

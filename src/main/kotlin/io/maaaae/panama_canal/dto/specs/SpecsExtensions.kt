package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.domain.api_info.ApiInfo
import io.maaaae.panama_canal.dto.dynamic_route_config.toDynamicRouteConfigResponse

fun ApiInfo.toSpecDto() = SpecsDto(
    id = this.apiId,
    name = this.name,
    endpoint = this.endpoint,
    method = this.method,
    response = this.response,
    request = this.request,
    dynamicRouteConfig = this.routeConfig?.toDynamicRouteConfigResponse(),
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

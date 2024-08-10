package io.maaaae.panama_canal.dto.dynamic_route_config

import io.maaaae.panama_canal.dto.filter_config.FilterConfigResponse

data class DynamicRouteConfigResponse(
    val id: Long,
    val uri: String,
    val predicate: String,
    val routeOrder: Int,
    val filters: List<FilterConfigResponse>
)

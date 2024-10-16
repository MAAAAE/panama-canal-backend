package io.maaaae.panama_canal.dto.dynamic_route_config

import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest

data class DynamicRouteConfigRequest(
    val uri: String,
    val predicate: String,
    val filters: List<FilterConfigRequest>,
    val routeOrder: Int,
)

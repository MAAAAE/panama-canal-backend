package io.maaaae.panama_canal.dto.dynamic_route_config

import io.maaaae.panama_canal.dto.filter_config.FilterConfigUpdateRequest

data class DynamicRouteConfigUpdateRequest(
    val uri: String? = null,
    val predicate: String? = null,
    val filters: List<FilterConfigUpdateRequest>? = null,
    val routeOrder: Int? = null,
)
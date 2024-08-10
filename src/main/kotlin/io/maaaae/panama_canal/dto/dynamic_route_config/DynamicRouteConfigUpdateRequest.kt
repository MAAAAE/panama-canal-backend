package io.maaaae.panama_canal.dto.dynamic_route_config

import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest

data class DynamicRouteConfigUpdateRequest(
    val uri: String? = null,
    val predicate: String? = null,
    val filters: List<FilterConfigRequest>? = null,
    val routeOrder: Int? = null,
)
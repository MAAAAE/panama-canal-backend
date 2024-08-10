package io.maaaae.panama_canal.dto.route_config

data class RouteConfigDto(
    val uri: String,
    val predicates: String,
    val filters: String,
    val routeOrder: Int,
)
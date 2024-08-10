package io.maaaae.panama_canal.dto.dynamic_route_config

import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.dto.filter_config.toResponse

fun DynamicRouteConfig.toDynamicRouteConfigResponse() = DynamicRouteConfigResponse(
    id = this.id,
    uri = this.uri,
    routeOrder = this.routeOrder,
    predicate = this.predicates,
    filters = this.filterConfigs.map { it.toResponse() }
)

fun DynamicRouteConfigRequest.toDynamicRouteConfigEntity() = DynamicRouteConfig(
    uri = this.uri,
    predicates = this.predicate,
    routeOrder = this.routeOrder,
)
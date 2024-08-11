package io.maaaae.panama_canal.dto.filter_config

import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.domain.FilterConfig

fun FilterConfig.toResponse() = FilterConfigResponse(
//    id = categoryId,
//    name = name,
//    domain = domain,
//    secret = "secret",
//    description = description
)

fun FilterConfigRequest.toCreateEntity() = FilterConfig(
    filterName = "test",
    param = "test",
    value = "test",
    dynamicRouteConfig = DynamicRouteConfig(
        uri = "",
        predicates = "",
        filters = "",
        routeOrder = 1
    )
)

fun FilterConfig.setRoute(dynamicRouteConfig: DynamicRouteConfig) {

}

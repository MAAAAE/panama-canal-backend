package io.maaaae.panama_canal.dto.filter_config

import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.domain.FilterConfig

fun FilterConfig.toResponse() = FilterConfigResponse(
    id = this.id,
    filterName = this.filterName,
    param = this.param,
    value = this.value,
)

fun FilterConfigRequest.toCreateEntity(dynamicRouteConfig: DynamicRouteConfig) = FilterConfig(
    filterName = this.filterName,
    param = this.param,
    value = this.value,
    dynamicRouteConfig = dynamicRouteConfig
)

fun FilterConfig.setRoute(dynamicRouteConfig: DynamicRouteConfig) {

}

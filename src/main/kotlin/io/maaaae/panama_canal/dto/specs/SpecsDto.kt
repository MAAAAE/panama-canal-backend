package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse

data class SpecsDto(
    val id: Long,
    val name: String,
    val endpoint: String,
    val method: Method,
    val request: String,
    val response: String,
    val dynamicRouteConfig: DynamicRouteConfigResponse?,
)

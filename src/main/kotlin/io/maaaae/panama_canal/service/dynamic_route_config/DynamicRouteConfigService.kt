package io.maaaae.panama_canal.service.dynamic_route_config

import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigUpdateRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigOptions


interface DynamicRouteConfigService {

    fun deleteDynamicRouteConfig(id: Long)

    fun getAllDynamicRouteConfigs(): List<DynamicRouteConfigResponse>

    fun createDynamicRouteConfig(dynamicRouteConfigRequest: DynamicRouteConfigRequest)

    fun updateDynamicRouteConfig(id: Long, request: DynamicRouteConfigUpdateRequest)
    fun getDynamicRouteConfigOptions(): List<DynamicRouteConfigOptions>

    fun publishEvent(message: String)
}

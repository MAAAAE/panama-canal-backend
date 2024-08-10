package io.maaaae.panama_canal.service.route_config

import io.maaaae.panama_canal.dto.route_config.RouteConfigDto

interface RouteConfigService {
    fun getAllRouteConfigs(): List<RouteConfigDto>
}
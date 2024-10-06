package io.maaaae.panama_canal.dto.dynamic_route_config

data class DynamicRouteConfigEvent (
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
){
}

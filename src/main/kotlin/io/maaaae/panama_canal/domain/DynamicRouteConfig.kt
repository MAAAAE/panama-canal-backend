package io.maaaae.panama_canal.domain

import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigRequest
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigResponse
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "dynamic_route_config")
data class DynamicRouteConfig(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val uri: String,
    val predicates: String,
    val filters: String,
    val routeOrder: Int
) {
    fun update(dynamicRouteConfigRequest: DynamicRouteConfigRequest) {
        TODO("Not yet implemented")
    }

    fun toResponse(): DynamicRouteConfigResponse {
        return DynamicRouteConfigResponse()
    }
}
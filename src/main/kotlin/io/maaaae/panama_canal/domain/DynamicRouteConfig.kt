package io.maaaae.panama_canal.domain

import io.maaaae.panama_canal.domain.api_info.ApiInfo
import io.maaaae.panama_canal.dto.dynamic_route_config.DynamicRouteConfigUpdateRequest
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "dynamic_route_config")
data class DynamicRouteConfig(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var uri: String,
    var predicates: String,
    var routeOrder: Int,
    @OneToMany(mappedBy = "dynamicRouteConfig", orphanRemoval = true)
    var filterConfigs: MutableList<FilterConfig> = mutableListOf(),
    @OneToMany(mappedBy = "routeConfig", orphanRemoval = true)
    var apiInfos: MutableList<ApiInfo> = mutableListOf()
) {
    fun update(request: DynamicRouteConfigUpdateRequest) {
        request.routeOrder?.let { this.routeOrder = it }
        request.uri?.let { this.uri = it }
        request.predicate?.let { this.predicates = it }
    }
}
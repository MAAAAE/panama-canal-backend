package io.maaaae.panama_canal.domain

import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import jakarta.persistence.*

@Entity
@Table(name = "filter_config")
data class FilterConfig(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    var dynamicRouteConfig: DynamicRouteConfig,
    var filterName: String,
    var param: String,
    var value: String
) {
    fun update(filterConfigRequest: FilterConfigRequest) {
        TODO("Not yet implemented")
    }

    fun setRoute(dynamicRouteConfig: DynamicRouteConfig) {
        this.dynamicRouteConfig = dynamicRouteConfig
    }
}
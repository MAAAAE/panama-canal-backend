package io.maaaae.panama_canal.domain

import io.maaaae.panama_canal.common.constant.PanamaConfig
import io.maaaae.panama_canal.dto.filter_config.FilterConfigRequest
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.BatchSize

@Entity
@Table(name = "filter_config")
@BatchSize(size = PanamaConfig.BATCH_SIZE)
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
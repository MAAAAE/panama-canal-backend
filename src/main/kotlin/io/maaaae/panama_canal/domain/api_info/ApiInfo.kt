package io.maaaae.panama_canal.domain.api_info

import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.common.constant.PanamaConfig
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.domain.DynamicRouteConfig
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.BatchSize

@Entity
@Table(name = "api_info")
@BatchSize(size = PanamaConfig.BATCH_SIZE)
data class ApiInfo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var apiId: Long = 0,
    var name: String,
    var endpoint: String,
    @Enumerated(EnumType.STRING)
    var method: Method,
    @Column(columnDefinition = "TEXT")
    var request: String,
    @Column(columnDefinition = "TEXT")
    var response: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_config_id", updatable = false)
    var routeConfig: DynamicRouteConfig? = null,
    @ManyToOne @JoinColumn(name = "category_id", updatable = false)
    var category: Category
) {
    fun update(specsUpdateRequest: SpecsUpdateRequest) {
        specsUpdateRequest.endpoint?.let { this.endpoint = it }
        specsUpdateRequest.method?.let { this.method = it }
        specsUpdateRequest.name?.let { this.name = it }
        specsUpdateRequest.request?.let { this.request = it }
        specsUpdateRequest.response?.let { this.response = it }
    }
}
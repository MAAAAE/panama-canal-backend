package io.maaaae.panama_canal.domain.api_info

import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "api_info")
data class ApiInfo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var apiId: Long = 0,
    var name: String,
    var endpoint: String,
    @Enumerated(EnumType.STRING)
    var method: Method,
    var headers: String,
    @ManyToOne @JoinColumn(name = "category_id")
    var category: Category
) {
    fun update(specsUpdateRequest: SpecsUpdateRequest) {
        specsUpdateRequest.endpoint?.let { this.endpoint = it }
        specsUpdateRequest.headers?.let { this.headers = it }
        specsUpdateRequest.method?.let { this.method = it }
    }
}
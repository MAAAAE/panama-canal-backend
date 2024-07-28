package io.maaaae.panama_canal.domain.api_info

import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.domain.Category
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
    val apiId: Long = 0,
    val name: String,
    val endpoint: String,
    @Enumerated(EnumType.STRING)
    val method: Method,
    @ManyToOne @JoinColumn(name = "category_id")
    val category: Category
)
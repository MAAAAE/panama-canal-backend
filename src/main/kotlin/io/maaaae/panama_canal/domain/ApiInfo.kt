package io.maaaae.panama_canal.domain

import jakarta.persistence.*

@Entity
data class ApiInfo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val apiId: Long = 0,
    val name: String,
    val endpoint: String,
    val method: String,
    @ManyToOne @JoinColumn(name = "category_id")
    val category: Category
)
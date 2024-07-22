package io.maaaae.panama_canal.domain

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val categoryId: Long = 0,
    val name: String,
    val description: String
)
package io.maaaae.panama_canal.domain

import io.maaaae.panama_canal.dto.category.CategoryRequest
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "category")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val categoryId: Long = 0,
    var name: String,
    var description: String,
    var domain: String,
) {
    fun update(categoryRequest: CategoryRequest) {
        name = categoryRequest.name
        description = categoryRequest.description
        domain = categoryRequest.domain
    }
}
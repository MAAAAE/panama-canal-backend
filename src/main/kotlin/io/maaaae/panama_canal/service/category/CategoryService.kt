package io.maaaae.panama_canal.service.category

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse

interface CategoryService {
    fun getAllCategories(): List<CategoryResponse>
    fun createCategory(categoryRequest: CategoryRequest)
    fun updateCategory(id: Long, categoryRequest: CategoryRequest)
    fun deleteCategory(id: Long)
}

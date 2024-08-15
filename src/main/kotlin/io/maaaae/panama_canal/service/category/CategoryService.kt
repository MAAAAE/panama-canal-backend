package io.maaaae.panama_canal.service.category

import io.maaaae.panama_canal.dto.category.CategoryOptionResponse
import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse

interface CategoryService {
    fun getAllCategories(): List<CategoryResponse>
    fun getCategoryById(id: Long): CategoryResponse
    fun getAllCategoryOptions(): List<CategoryOptionResponse>
    fun createCategory(categoryRequest: CategoryRequest)
    fun updateCategory(id: Long, categoryRequest: CategoryRequest)
    fun deleteCategory(id: Long)
}

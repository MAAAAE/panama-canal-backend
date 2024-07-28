package io.maaaae.panama_canal.service.category

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import org.springframework.stereotype.Service


@Service
class CategoryServiceImpl: CategoryService {

    override fun getAllCategories(): List<CategoryResponse> {
        TODO("Not yet implemented")
    }

    override fun createCategory(categoryRequest: CategoryRequest) {
        TODO("Not yet implemented")
    }

    override fun updateCategory(id: String, categoryRequest: CategoryRequest) {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(id: String) {
        TODO("Not yet implemented")
    }
}
package io.maaaae.panama_canal.service.category

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import io.maaaae.panama_canal.dto.category.toCreateEntity
import io.maaaae.panama_canal.dto.category.toResponse
import io.maaaae.panama_canal.repository.category.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {

    @Transactional
    override fun getAllCategories(): List<CategoryResponse> {
        return categoryRepository.findAll().asSequence()
            .map { it.toResponse() }
            .toList()
    }

    @Transactional
    override fun createCategory(categoryRequest: CategoryRequest) {
        categoryRepository.save(categoryRequest.toCreateEntity())
    }

    @Transactional
    override fun updateCategory(id: Long, categoryRequest: CategoryRequest) {
        val category = categoryRepository.findById(id)
            .orElseThrow {
                NoSuchElementException("category not found.")
            }

        category.update(categoryRequest)
    }

    @Transactional
    override fun deleteCategory(id: Long) {
        categoryRepository.deleteById(id)
    }
}
package io.maaaae.panama_canal.service.category

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.category.CategoryOptionResponse
import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import io.maaaae.panama_canal.dto.category.toCreateEntity
import io.maaaae.panama_canal.dto.category.toOptionResponse
import io.maaaae.panama_canal.dto.category.toResponse
import io.maaaae.panama_canal.repository.category.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
) : CategoryService {

    @Transactional(readOnly = true)
    override fun getAllCategories(): List<CategoryResponse> {
        return categoryRepository.findAll().asSequence()
            .map { it.toResponse() }
            .toList()
    }

    @Transactional(readOnly = true)
    override fun getCategoryById(id: Long): CategoryResponse =
        categoryRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("Category Not found. id: $id")
        }.toResponse()

    @Transactional(readOnly = true)
    override fun getAllCategoryOptions(): List<CategoryOptionResponse> {
        return categoryRepository.findAll()
            .map { it.toOptionResponse() }
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
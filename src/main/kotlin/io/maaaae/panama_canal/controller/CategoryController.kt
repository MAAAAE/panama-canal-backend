package io.maaaae.panama_canal.controller

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import io.maaaae.panama_canal.service.category.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/all")
    fun getCategories(): ResponseEntity<List<CategoryResponse>> {
        return ResponseEntity.ok(categoryService.getAllCategories())
    }

    @PostMapping
    fun createCategory(categoryRequest: CategoryRequest): ResponseEntity<Void> {
        categoryService.createCategory(categoryRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: String, @RequestBody categoryRequest: CategoryRequest): ResponseEntity<Void> {
        categoryService.updateCategory(id, categoryRequest)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: String): ResponseEntity<Void> {
        categoryService.deleteCategory(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
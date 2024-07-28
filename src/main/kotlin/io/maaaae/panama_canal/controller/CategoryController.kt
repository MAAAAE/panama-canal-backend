package io.maaaae.panama_canal.controller

import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/category")
class CategoryController() {

    @GetMapping("/all")
    fun getCategories(): ResponseEntity<List<CategoryResponse>> {
        return ResponseEntity.ok(listOf())
    }

    @PostMapping
    fun createCategory(categoryRequest: CategoryRequest): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.CREATED)
    }
    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: String): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.OK)
    }
    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: String): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.OK)
    }
}
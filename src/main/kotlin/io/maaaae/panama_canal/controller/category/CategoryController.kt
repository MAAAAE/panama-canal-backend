package io.maaaae.panama_canal.controller.category

import io.maaaae.panama_canal.dto.category.CategoryOptionResponse
import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.dto.category.CategoryResponse
import io.maaaae.panama_canal.service.category.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/all")
    fun getCategories(): ResponseEntity<List<CategoryResponse>> =
        ResponseEntity.ok(categoryService.getAllCategories())

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<CategoryResponse> =
        ResponseEntity.ok(categoryService.getCategoryById(id))

    @GetMapping("/options")
    fun getCategoryOptions(): ResponseEntity<List<CategoryOptionResponse>> =
        ResponseEntity.ok(categoryService.getAllCategoryOptions())

    @PostMapping
    fun createCategory(@RequestBody @Valid categoryRequest: CategoryRequest): ResponseEntity<Void> {
        categoryService.createCategory(categoryRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody @Valid categoryRequest: CategoryRequest): ResponseEntity<Void> {
        categoryService.updateCategory(id, categoryRequest)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        categoryService.deleteCategory(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
package io.maaaae.panama_canal.controller

import io.kotest.core.spec.style.DescribeSpec
import io.maaaae.panama_canal.controller.advice.GlobalExceptionHandler
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.dto.category.CategoryRequest
import io.maaaae.panama_canal.service.category.CategoryService
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@WebMvcTest(controllers = [CategoryController::class])
class CategoryControllerTest : DescribeSpec({

    val categoryService: CategoryService = mockk<CategoryService>()
    val categoryController = CategoryController(categoryService)
    val tmockMvc = MockMvcBuilders
        .standaloneSetup(categoryController)
        .setControllerAdvice(GlobalExceptionHandler())
        .build()


    describe("category") {

        val categoryRequest: CategoryRequest = CategoryRequest(
            name = "random",
            domain = "domain",
            description = "desc",
            secret = "secret"
        )

        val category = Category(
            categoryId = 1L,
            name = "random",
            description = "desc",
            domain = "domain"

        )

        every { categoryService.getAllCategories() } returns listOf()
        every { categoryService.createCategory(categoryRequest) } returns

        context("GET /category") {

            it("should return list of category") {

                tmockMvc.get("/category") {
                    contentType = MediaType.APPLICATION_JSON
                    accept = MediaType.APPLICATION_JSON
                }.andExpect {
                    status { MockMvcResultMatchers.status().isOk }
                }
            }
        }
        context("POST /category") {

            it("should create category") {

                tmockMvc.post("/category") {
                    contentType = MediaType.APPLICATION_JSON
                    accept = MediaType.APPLICATION_JSON
                }.andExpect {
                    status { MockMvcResultMatchers.status().isBadRequest }
                }
            }
        }
    }
})

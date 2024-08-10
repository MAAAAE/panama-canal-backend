//package io.maaaae.panama_canal.controller.specs
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import io.kotest.core.spec.style.DescribeSpec
//import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
//import io.maaaae.panama_canal.controller.CategoryController
//import io.maaaae.panama_canal.controller.advice.GlobalExceptionHandler
//import io.maaaae.panama_canal.domain.Category
//import io.maaaae.panama_canal.dto.category.CategoryRequest
//import io.maaaae.panama_canal.service.category.CategoryService
//import io.mockk.every
//import io.mockk.mockk
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.delete
//import org.springframework.test.web.servlet.get
//import org.springframework.test.web.servlet.post
//import org.springframework.test.web.servlet.put
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//
//
//@WebMvcTest(controllers = [CategoryController::class])
//class CategoryControllerTest : DescribeSpec({
//
//    val objectMapper = ObjectMapper()
//    val categoryService: CategoryService = mockk<CategoryService>()
//    val categoryController = CategoryController(categoryService)
//    val mockMvc = MockMvcBuilders
//        .standaloneSetup(categoryController)
//        .setControllerAdvice(GlobalExceptionHandler())
//        .build()
//
//
//    describe("category") {
//
//        val categoryRequest: CategoryRequest = CategoryRequest(
//            name = "random",
//            domain = "domain",
//            description = "desc",
//            secret = "secret"
//        )
//
//        val category = Category(
//            categoryId = 1L,
//            name = "random",
//            description = "desc",
//            domain = "domain"
//
//        )
//
//        val invalidId = 999
//
//
//        every { categoryService.getAllCategories() } returns listOf()
//        every { categoryService.createCategory(categoryRequest) } returns Unit
//
//        context("GET /category") {
//
//            it("should return list of category") {
//
//                mockMvc.get("/category") {
//                    contentType = MediaType.APPLICATION_JSON
//                    accept = MediaType.APPLICATION_JSON
//                }.andExpect {
//                    status { MockMvcResultMatchers.status().isOk }
//                }
//            }
//        }
//        context("POST /category") {
//
//            it("should create category") {
//
//                mockMvc.post("/category") {
//                    contentType = MediaType.APPLICATION_JSON
//                    accept = MediaType.APPLICATION_JSON
//                    content = objectMapper.writeValueAsString(categoryRequest)
//                }.andExpect {
//                    status { MockMvcResultMatchers.status().isOk }
//                }
//            }
//        }
//        context("PUT /category") {
//
//            val updateRequest = CategoryRequest(
//                name = "name",
//                domain = "new domain",
//            )
//            every { categoryService.updateCategory(neq(1), any()) } throws ResourceNotFoundException("invalid ID is given")
//            every { categoryService.updateCategory(eq(1), eq(updateRequest)) } returns Unit
//
//            it("should update category properties") {
//
//                mockMvc.put("/category/1") {
//                    contentType = MediaType.APPLICATION_JSON
//                    accept = MediaType.APPLICATION_JSON
//                    content = objectMapper.writeValueAsString(updateRequest)
//                }.andExpect {
//                    status { isOk() }
//                }
//            }
//            it("should throw exception if ID is not exist") {
//
//                mockMvc.put("/category/${invalidId}") {
//                    contentType = MediaType.APPLICATION_JSON
//                    accept = MediaType.APPLICATION_JSON
//                    content = objectMapper.writeValueAsString(updateRequest)
//                }.andExpect {
//                    status { isNotFound() }
//                }
//            }
//        }
//
//        context("DELETE /category") {
//
//            val deleteId = 1L
//
//            every { categoryService.deleteCategory(deleteId) } returns Unit
//            every { categoryService.deleteCategory(neq(1)) } throws ResourceNotFoundException("invalid ID is given")
//
//            it("should return OK when remove item") {
//
//                mockMvc.delete("/category/1") { }
//                    .andExpect {
//                        status { isOk() }
//                    }
//            }
//            it("should return Bad Request when id is invalid") {
//
//                mockMvc.delete("/category/${invalidId}") { }
//                    .andExpect {
//                        status { isNotFound() }
//                    }
//            }
//        }
//    }
//})

package io.maaaae.panama_canal.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.maaaae.panama_canal.controller.advice.GlobalExceptionHandler
import io.maaaae.panama_canal.service.category.CategoryService
import io.mockk.every
import io.mockk.mockk
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@WebMvcTest(controllers = [CategoryController::class])
class CategoryControllerTest: BehaviorSpec({

    val categoryService: CategoryService = mockk<CategoryService>()
    val categoryController = CategoryController(categoryService)
    val tmockMvc = MockMvcBuilders
        .standaloneSetup(categoryController)
        .setControllerAdvice(GlobalExceptionHandler())
        .build()


    given("category") {

        every { categoryService.getAllCategories() } returns listOf()

        `when`("get all") {

            tmockMvc.get("/category") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { MockMvcResultMatchers.status().isOk }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }
    }
})

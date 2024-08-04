package io.maaaae.panama_canal.controller.specs

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.controller.advice.GlobalExceptionHandler
import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest
import io.maaaae.panama_canal.service.SpecsService
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@WebMvcTest(SpecController::class)
class SpecControllerTest : DescribeSpec({
    val specsService: SpecsService = mockk<SpecsService>()
    val objectMapper = ObjectMapper()

    val specController = SpecController(specsService = specsService)
    val validator = LocalValidatorFactoryBean().apply {
        afterPropertiesSet()
    }
    val mockMvc = MockMvcBuilders
        .standaloneSetup(specController)
        .setValidator(validator)
        .setControllerAdvice(GlobalExceptionHandler())
        .build()

    afterContainer {
        clearAllMocks()
    }

    val specsDto = SpecsDto(
        endpoint = "/test",
        method = Method.GET,
        headers = "Content-Type: application/json",
        categoryId = 1L
    )
    val specsRequest = SpecsRequest(
        name = "API Spec",
        endpoint = "/test",
        method = Method.GET,
        categoryId = 1L,
        customRoute = "/custom",
        headers = "Content-Type: application/json"
    )

    describe("SpecController") {
        context("GET /specs/all") {
            it("should return all API specs") {
                every { specsService.getAllApiSpecs() } returns listOf(specsDto)

                mockMvc.perform(MockMvcRequestBuilders.get("/specs/all"))
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].endpoint").value(specsDto.endpoint))
            }
        }

        context("GET /specs/category/{categoryId}") {
            val categoryId = 1L
            it("should return API Spec of given categoryId") {
                every { specsService.getApiSpecByCategoryId(categoryId) } returns listOf(specsDto)

                mockMvc.perform(MockMvcRequestBuilders.get("/specs/category/$categoryId"))
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].endpoint").value(specsDto.endpoint))
            }

            it("should return 404 Not Found when category not found") {
                val errorMessage = "Category Not Found categoryId: $categoryId"
                every { specsService.getApiSpecByCategoryId(categoryId) } throws ResourceNotFoundException(errorMessage)

                mockMvc.perform(MockMvcRequestBuilders.get("/specs/category/$categoryId"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound)
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(HttpStatus.NOT_FOUND.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(errorMessage))
            }

            it("should return 400 Bad Request when categoryId is invalid") {
                val badCategoryId = "asdf"
                val errorMessage = "Failed to convert value of type 'java.lang.String' to required type 'long'; For input string: \"$badCategoryId\""

                mockMvc.perform(MockMvcRequestBuilders.get("/specs/category/$badCategoryId"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest)
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(errorMessage))

            }
        }

        context("POST /specs") {
            it("should create new API spec") {
                every { specsService.createApiSpecs(any()) } returns specsDto

                mockMvc.perform(
                    MockMvcRequestBuilders.post("/specs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specsRequest))
                )
                    .andExpect(MockMvcResultMatchers.status().isCreated)
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.endpoint").value(specsDto.endpoint))
            }

            val testCases = listOf(
                Triple(
                    specsRequest.copy(name = ""),
                    "Title must not be blank",
                    "name"
                ),
                Triple(
                    specsRequest.copy(endpoint = "e".repeat(256)),
                    "Endpoint must be less than 255",
                    "endpoint"
                ),
                Triple(
                    specsRequest.copy(categoryId = -1L),
                    "Category Id must be positive digit",
                    "categoryId"
                ),
                Triple(
                    specsRequest.copy(customRoute = ""),
                    "Custom Route must not be blank",
                    "customRoute"
                ),
                Triple(
                    specsRequest.copy(headers = ""),
                    "Headers must not be blank",
                    "headers"
                )
            )

            testCases.forEach { (invalidRequest, expectedErrorMessage, fieldName) ->
                it("should return validation error when '$fieldName' is invalid") {
                    println(objectMapper.writeValueAsString(invalidRequest))
                    mockMvc.perform(
                        MockMvcRequestBuilders.post("/specs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(invalidRequest))
                    )
                        .andExpect(MockMvcResultMatchers.status().isBadRequest)
                        .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(HttpStatus.BAD_REQUEST.value()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(expectedErrorMessage))

                }
            }
        }

        context("PUT /specs/{id}") {
            val specId = 1L
            val specsUpdateRequest = SpecsUpdateRequest(
                endpoint = "/new-endpoint",
                method = Method.POST,
                headers = "new-header: value"
            )
            val updatedSpecDto = SpecsDto(
                endpoint = "/new-endpoint",
                method = Method.POST,
                categoryId = 1L,
                headers = "new-header: value"
            )
            it("should update the API spec and return the updated spec") {

                every {
                    specsService.updateApiSpec(
                        specId = specId,
                        specsUpdateRequest = specsUpdateRequest
                    )
                } returns updatedSpecDto

                mockMvc.perform(
                    MockMvcRequestBuilders.put("/specs/$specId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specsUpdateRequest))
                )
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.endpoint").value(updatedSpecDto.endpoint))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.method").value(updatedSpecDto.method.toString()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.headers").value(updatedSpecDto.headers))

                verify(exactly = 1) { specsService.updateApiSpec(specId, specsUpdateRequest) }
            }

            it("should return not found status if the API spec does not exist") {
                every {
                    specsService.updateApiSpec(
                        specId = specId,
                        specsUpdateRequest = specsUpdateRequest
                    )
                } throws ResourceNotFoundException("API spec not found")

                mockMvc.perform(
                    MockMvcRequestBuilders.put("/specs/$specId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specsUpdateRequest))
                )
                    .andExpect(MockMvcResultMatchers.status().isNotFound)
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(HttpStatus.NOT_FOUND.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("API spec not found"))

                verify { specsService.updateApiSpec(any(), any()) }
            }
        }

        context("DELETE /specs/{id}") {
            it("should remove api specs") {
                val id = 1L
                every { specsService.deleteApiSpec(id) } returns Unit

                mockMvc.perform(MockMvcRequestBuilders.delete("/specs/$id"))
                    .andExpect(MockMvcResultMatchers.status().isNoContent)

                verify(exactly = 1) { specsService.deleteApiSpec(id) }
            }

            it("should return bad request status if the API sepc does not exist") {
                val id = 1L
                val errorMessage = "API Spec Not Found. specId: $id"
                every { specsService.deleteApiSpec(id) } throws ResourceNotFoundException(errorMessage)

                mockMvc.perform(MockMvcRequestBuilders.delete("/specs/$id"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(HttpStatus.NOT_FOUND.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(errorMessage))
            }
        }
    }
})

package io.maaaae.panama_canal.controller.specs

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.controller.advice.GlobalExceptionHandler
import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.service.SpecsService
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest(SpecController::class)
class SpecControllerTest : DescribeSpec({
    val specsService: SpecsService = mockk<SpecsService>()
    val objectMapper = ObjectMapper()

    val specController = SpecController(specsService = specsService)
    val mockMvc = MockMvcBuilders
        .standaloneSetup(specController)
        .setControllerAdvice(GlobalExceptionHandler())
        .build()

    afterContainer {
        clearAllMocks()
    }

    val specsDto = SpecsDto(
        endpoint = "/test",
        method = Method.GET,
        categoryId = 1L
    )
    val specsRequest = SpecsRequest(
        name = "API Spec",
        endpoint = "/test",
        method = Method.GET,
        categoryId = 1L,
        customRoute = "/custom",
        headers = "Header: value"
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
    }
})

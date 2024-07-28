package io.maaaae.panama_canal.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.domain.api_info.ApiInfo
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.repository.api_info.ApiInfoRepository
import io.maaaae.panama_canal.repository.category.CategoryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull

class SpecsServiceImplTest : BehaviorSpec({
    val apiInfoRepository = mockk<ApiInfoRepository>()
    val categoryRepository = mockk<CategoryRepository>()
    val specsService = SpecsServiceImpl(
        apiInfoRepository = apiInfoRepository,
        categoryRepository = categoryRepository
    )

    given("SpecService") {
        val category = Category(1, "Category1", "Description1")
        val apiInfo = ApiInfo(1, "API Spec", "/test", Method.GET, category)
        val apiInfoList = listOf(
            apiInfo,
            apiInfo.copy(apiId = 2)

        )
        val specsRequest = SpecsRequest(
            name = "API Spec",
            endpoint = "/test",
            method = Method.GET,
            categoryId = 1L,
            customRoute = "/custom",
            headers = "Header: value"
        )

        `when`("resources are found") {
            then("it should return a list of SpecDto") {
                // Given
                every { apiInfoRepository.findAll() } returns apiInfoList

                // When
                val result = specsService.getAllApiSpecs()

                // Then
                result.shouldNotBeEmpty()
                result.size shouldBe 2
                result[0].endpoint shouldBe "/test"
                verify { apiInfoRepository.findAll() }
            }
        }

        `when`("no resources are found") {
            then("it should throw ResourceNotFoundException") {
                // Given
                every { apiInfoRepository.findAll() } returns emptyList()

                // Then
                shouldThrow<ResourceNotFoundException> {
                    // When
                    specsService.getAllApiSpecs()
                }
                verify { apiInfoRepository.findAll() }
            }
        }

        `when`("category found") {
            then("it should insert new API Spec") {
                every { categoryRepository.findByIdOrNull(any()) } returns category
                every { apiInfoRepository.save(any()) } returns apiInfo

                val result = specsService.createApiSpecs(specsRequest)

                result.endpoint shouldBe specsRequest.endpoint
                result.method shouldBe specsRequest.method
            }
        }

        `when`("no category found") {
            then("it should throw ResourceNotFoundException") {
                every { categoryRepository.findByIdOrNull(any()) } returns null

                shouldThrow<ResourceNotFoundException> {
                    specsService.createApiSpecs(specsRequest)
                }

                verify { categoryRepository.findByIdOrNull(any()) }
            }
        }

        `when`("no api spec found for deletion") {
            then("it should throw ResourceNotFoundException") {
                val specId = 1L
                every { apiInfoRepository.findByIdOrNull(any()) } returns null

                shouldThrow<ResourceNotFoundException> {
                    specsService.deleteApiSpec(specId)
                }

                verify { apiInfoRepository.findByIdOrNull(any()) }
            }
        }
    }
})

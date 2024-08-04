package io.maaaae.panama_canal.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.domain.api_info.ApiInfo
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest
import io.maaaae.panama_canal.repository.api_info.ApiInfoRepository
import io.maaaae.panama_canal.repository.category.CategoryRepository
import io.mockk.clearAllMocks
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

    afterContainer {
        clearAllMocks()
    }

    given("SpecService") {
        val category = Category(1, "Category1", "Description1", "/endpoint")
        val apiInfo = ApiInfo(
            apiId = 1,
            name = "API Spec",
            endpoint = "/test",
            method = Method.GET,
            headers = "Content-Type: application/json",
            category = category)
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
        val specsUpdateRequest = SpecsUpdateRequest(
            endpoint = "/new-endpoint",
            method = Method.POST,
            headers = "new-header: value"
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

        `when`("requested api spec exist") {
            then("it should update exist api spec") {
                val specId = 1L
                val updatedSpec = apiInfo.apply {
                    update(specsUpdateRequest)
                }

                every { apiInfoRepository.findByIdOrNull(specId) } returns apiInfo
                every { apiInfoRepository.save(apiInfo) } returns updatedSpec

                val result = specsService.updateApiSpec(
                    specId = specId,
                    specsUpdateRequest = specsUpdateRequest
                )

                result shouldBe SpecsDto(
                    endpoint = "/new-endpoint",
                    method = Method.POST,
                    categoryId = 1L,
                    headers = "new-header: value")

                verify(exactly = 1) { apiInfoRepository.findByIdOrNull(specId) }
                verify(exactly = 1) { apiInfoRepository.save(apiInfo) }
            }
        }

        `when`("no api spec found for update") {
            then("it should throw ResourceNotFoundException") {
                val specId = 1L
                every { apiInfoRepository.findByIdOrNull(specId) } returns null

                shouldThrow<ResourceNotFoundException> {
                    specsService.updateApiSpec(specId = specId, specsUpdateRequest = specsUpdateRequest)
                }

                verify(exactly = 1) { apiInfoRepository.findByIdOrNull(specId) }
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

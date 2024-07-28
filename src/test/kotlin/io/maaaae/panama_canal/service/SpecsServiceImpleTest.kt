package io.maaaae.panama_canal.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.maaaae.panama_canal.common.constant.Method
import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.domain.api_info.ApiInfo
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.repository.api_info.ApiInfoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class SpecsServiceImpleTest : BehaviorSpec({
    val apiInfoRepository = mockk<ApiInfoRepository>()
    val specsServiceImpl = SpecsServiceImpl(apiInfoRepository)

    given("ApiInfoService") {
        `when`("resources are found") {
            then("it should return a list of ApiInfoDto") {
                // Given
                val category = Category(1, "Category1", "Description1")
                val apiInfoList = listOf(
                    ApiInfo(1, "get api", "openapi/sample/get", Method.GET, category),
                    ApiInfo(2, "post api", "openapi/sample", Method.POST, category)
                )
                every { apiInfoRepository.findAll() } returns apiInfoList

                // When
                val result = specsServiceImpl.getAllApiInfo()

                // Then
                result.shouldNotBeEmpty()
                result.size shouldBe 2
                result[0].endpoint shouldBe "openapi/sample/get"
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
                    specsServiceImpl.getAllApiInfo()
                }
                verify { apiInfoRepository.findAll() }
            }
        }
    }
})
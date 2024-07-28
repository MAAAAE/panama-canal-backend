package io.maaaae.panama_canal.service

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.domain.Category
import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.dto.specs.toApiInfoEntity
import io.maaaae.panama_canal.dto.specs.toSpecDto
import io.maaaae.panama_canal.repository.api_info.ApiInfoRepository
import io.maaaae.panama_canal.repository.category.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SpecsServiceImpl(
    private val apiInfoRepository: ApiInfoRepository,
    private val categoryRepository: CategoryRepository,
) : SpecsService {
    override fun getAllApiSpecs(): List<SpecsDto> {
        val apiInfos = apiInfoRepository.findAll()
            .ifEmpty { throw ResourceNotFoundException("Resource not found.") }
        return apiInfos.map { it.toSpecDto() }.toList()
    }

    override fun createApiSpecs(specsRequest: SpecsRequest): SpecsDto {
        val category = categoryRepository.findByIdOrNull(specsRequest.categoryId)
            ?: throw ResourceNotFoundException("Category not found. Please create category first. categoryId: ${specsRequest.categoryId}")
        val createdApiInfo = apiInfoRepository.save(specsRequest.toApiInfoEntity(category))
        return createdApiInfo.toSpecDto()
    }

    override fun deleteApiSpec(specId: Long) {
        val targetSpec = apiInfoRepository.findByIdOrNull(specId)
            ?: throw ResourceNotFoundException("API Spec Not Found. specId: $specId")
        apiInfoRepository.delete(targetSpec)
    }
}
package io.maaaae.panama_canal.service

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest
import io.maaaae.panama_canal.dto.specs.toApiInfoEntity
import io.maaaae.panama_canal.dto.specs.toSpecDto
import io.maaaae.panama_canal.repository.api_info.ApiInfoRepository
import io.maaaae.panama_canal.repository.category.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SpecsServiceImpl(
    private val apiInfoRepository: ApiInfoRepository,
    private val categoryRepository: CategoryRepository,
) : SpecsService {

    @Transactional(readOnly = true)
    override fun getAllApiSpecs(): List<SpecsDto> {
        val apiInfos = apiInfoRepository.findAll()
            .ifEmpty { throw ResourceNotFoundException("Resource not found.") }
        return apiInfos.map { it.toSpecDto() }.toList()
    }

    @Transactional
    override fun createApiSpecs(specsRequest: SpecsRequest): SpecsDto {
        val category = categoryRepository.findByIdOrNull(specsRequest.categoryId)
            ?: throw ResourceNotFoundException("Category not found. Please create category first. categoryId: ${specsRequest.categoryId}")
        val createdApiInfo = apiInfoRepository.save(specsRequest.toApiInfoEntity(category))
        return createdApiInfo.toSpecDto()
    }

    @Transactional
    override fun updateApiSpec(specId: Long, specsUpdateRequest: SpecsUpdateRequest): SpecsDto {
        val targetSpec = apiInfoRepository.findByIdOrNull(specId)
            ?: throw ResourceNotFoundException("API Spec Not Found. specId: $specId")

        targetSpec.update(specsUpdateRequest)
        val updatedSpec = apiInfoRepository.save(targetSpec)

        return updatedSpec.toSpecDto()
    }

    @Transactional
    override fun deleteApiSpec(specId: Long) {
        val targetSpec = apiInfoRepository.findByIdOrNull(specId)
            ?: throw ResourceNotFoundException("API Spec Not Found. specId: $specId")
        apiInfoRepository.delete(targetSpec)
    }
}
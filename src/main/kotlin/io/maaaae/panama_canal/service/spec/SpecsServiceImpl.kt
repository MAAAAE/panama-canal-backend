package io.maaaae.panama_canal.service.spec

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest
import io.maaaae.panama_canal.dto.specs.toApiInfoEntity
import io.maaaae.panama_canal.dto.specs.toSpecDto
import io.maaaae.panama_canal.repository.api_info.ApiInfoRepository
import io.maaaae.panama_canal.repository.category.CategoryRepository
import io.maaaae.panama_canal.repository.dynamic_route_config.DynamicRouteConfigRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SpecsServiceImpl(
    private val apiInfoRepository: ApiInfoRepository,
    private val categoryRepository: CategoryRepository,
    private val dynamicRouteConfigRepository: DynamicRouteConfigRepository,
) : SpecsService {

    @Transactional(readOnly = true)
    override fun getAllApiSpecs(): List<SpecsDto> {
        val apiInfos = apiInfoRepository.findAll()
            .ifEmpty { throw ResourceNotFoundException("Resource not found.") }
        return apiInfos.map { it.toSpecDto() }.toList()
    }

    @Transactional(readOnly = true)
    override fun getApiSpecByCategoryId(categoryId: Long): List<SpecsDto> {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw ResourceNotFoundException("Category not found. Please create category first. categoryId: $categoryId")
        return apiInfoRepository.findByCategory(category)
            .map { it.toSpecDto() }.toList()
    }

    @Transactional
    override fun createApiSpecs(specsRequest: SpecsRequest): SpecsDto {
        val category = categoryRepository.findByIdOrNull(specsRequest.categoryId)
            ?: throw ResourceNotFoundException("Category not found. Please create category first. categoryId: ${specsRequest.categoryId}")

        val routeConfig = dynamicRouteConfigRepository.findByIdOrNull(specsRequest.customRouteId)
            ?:throw ResourceNotFoundException("Dynamic Route Config not found. Please check dynamic route config. RouteId: ${specsRequest.customRouteId}")

        val createdApiInfo = apiInfoRepository.save(specsRequest.toApiInfoEntity(
            category=category,
            routeConfig=routeConfig)
        )
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
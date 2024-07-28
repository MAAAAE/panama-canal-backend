package io.maaaae.panama_canal.service

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.toDto
import io.maaaae.panama_canal.repository.api_info.ApiInfoRepository
import org.springframework.stereotype.Service

@Service
class SpecsServiceImpl(private val apiInfoRepository: ApiInfoRepository) : SpecsService {
    override fun getAllApiInfo(): List<SpecsDto> {
        val apiInfos = apiInfoRepository.findAll()
            .ifEmpty { throw ResourceNotFoundException("Resource not found.") }
        return apiInfos.map { it.toDto() }.toList()
    }
}
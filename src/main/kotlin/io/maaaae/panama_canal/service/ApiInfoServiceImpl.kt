package io.maaaae.panama_canal.service

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import io.maaaae.panama_canal.dto.ApiInfoDto
import io.maaaae.panama_canal.dto.toDto
import io.maaaae.panama_canal.repository.ApiInfoRepository
import org.springframework.stereotype.Service

@Service
class ApiInfoServiceImpl(private val apiInfoRepository: ApiInfoRepository) : ApiInfoService {
    override fun getAllApiInfo(): List<ApiInfoDto> {
        val apiInfos = apiInfoRepository.findAll()
            .ifEmpty { throw ResourceNotFoundException("Resource not found.") }
        return apiInfos.map { it.toDto() }.toList()
    }
}
package io.maaaae.panama_canal.service.spec

import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest
import io.maaaae.panama_canal.dto.specs.SpecsUpdateRequest

interface SpecsService {
    fun getAllApiSpecs(): List<SpecsDto>
    fun getApiSpecByCategoryId(categoryId: Long): List<SpecsDto>
    fun createApiSpecs(specsRequest: SpecsRequest): SpecsDto
    fun deleteApiSpec(specId: Long)
    fun updateApiSpec(specId: Long, specsUpdateRequest: SpecsUpdateRequest): SpecsDto
}
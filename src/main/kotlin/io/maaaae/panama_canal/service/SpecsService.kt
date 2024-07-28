package io.maaaae.panama_canal.service

import io.maaaae.panama_canal.dto.specs.SpecsDto
import io.maaaae.panama_canal.dto.specs.SpecsRequest

interface SpecsService {
    fun getAllApiSpecs(): List<SpecsDto>
    fun createApiSpecs(specsRequest: SpecsRequest): SpecsDto
}
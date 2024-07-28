package io.maaaae.panama_canal.service

import io.maaaae.panama_canal.dto.specs.SpecsDto

interface SpecsService {
    fun getAllApiInfo(): List<SpecsDto>
}
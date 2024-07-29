package io.maaaae.panama_canal.repository.category

import io.maaaae.panama_canal.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
}
package com.codely.domain

import java.time.LocalDateTime
import java.util.UUID

data class Course(
    val id: UUID,
    val name: String,
    val createdAt: LocalDateTime
)

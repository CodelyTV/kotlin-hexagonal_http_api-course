package com.codely.course.domain

import java.time.LocalDateTime
import java.util.UUID

open class DomainEvent(val type: String, val payload: String) {
    private val id = UUID.randomUUID()
    private val occurredOn = LocalDateTime.now()
}

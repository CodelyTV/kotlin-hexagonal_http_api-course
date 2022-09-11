package com.codely.shared.event

import java.time.LocalDateTime
import java.util.UUID

open class DomainEvent(val type: String, val payload: String) {
    private val id = UUID.randomUUID()
    private val occurredOn = LocalDateTime.now()
}

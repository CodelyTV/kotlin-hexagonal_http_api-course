package com.codely.course.infrastructure

import com.codely.course.domain.DomainEvent
import com.codely.course.domain.Publisher

class InMemoryPublisher : Publisher {
    private val events = mutableListOf<DomainEvent>()
    override fun publish(data: List<DomainEvent>) {
        events += data
    }

    override fun get(): List<DomainEvent> {
        return events
    }

    override fun flush() {
        events.clear()
    }
}

package com.codely.shared.event

import com.codely.course.domain.course.Publisher

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

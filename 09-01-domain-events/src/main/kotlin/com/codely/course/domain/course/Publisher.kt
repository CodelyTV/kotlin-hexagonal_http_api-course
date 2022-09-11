package com.codely.course.domain.course

import com.codely.shared.event.DomainEvent

interface Publisher {
    fun publish(events: List<DomainEvent>)
    fun get(): List<DomainEvent>
    fun flush()
}

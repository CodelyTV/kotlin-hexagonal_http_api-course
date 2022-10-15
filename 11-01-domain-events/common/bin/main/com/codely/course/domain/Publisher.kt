package com.codely.course.domain

interface Publisher {
    fun publish(events: List<DomainEvent>)
    fun get(): List<DomainEvent>
    fun flush()
}

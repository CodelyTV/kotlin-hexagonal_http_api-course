package com.codely.course.domain

import java.time.LocalDateTime
import java.util.UUID

data class CourseId(val value: UUID) {
    companion object {
        fun fromString(id: String) = try {
            CourseId(UUID.fromString(id))
        } catch (exception: Exception) {
            throw InvalidCourseIdException(id, exception)
        }
    }
}
data class CourseName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.isBlank()) {
            throw InvalidCourseNameException(value)
        }
    }
}

data class Course(
    val id: CourseId,
    val name: CourseName,
    val createdAt: LocalDateTime,
    val events: List<DomainEvent>
) {
    companion object {
        fun create(
            id: CourseId,
            name: CourseName
        ) = LocalDateTime.now().let { createdAt ->
            Course(id, name, createdAt, listOf(CourseCreated(id, name, createdAt)))
        }
    }
}

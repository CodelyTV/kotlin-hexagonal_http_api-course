package com.codely.course.domain

import java.time.LocalDateTime

data class CourseCreated(
    val courseId: CourseId,
    val courseName: CourseName,
    val createdAt: LocalDateTime
) : DomainEvent(
    type = "CourseCreated",
    payload = """
    {
        "id": ${courseId.value},
        "name": ${courseName.value},
        "created_at": $createdAt
    }
    """.trimIndent()
)

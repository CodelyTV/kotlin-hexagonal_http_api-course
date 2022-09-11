package com.codely.course.domain.course

import com.codely.shared.event.DomainEvent
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

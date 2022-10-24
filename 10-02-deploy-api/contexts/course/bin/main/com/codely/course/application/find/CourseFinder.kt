package com.codely.course.application.find

import com.codely.course.domain.Course
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseRepository
import java.time.LocalDateTime

class CourseFinder(private val courseRepository: CourseRepository) {
    fun execute(courseId: String): Result<CourseResponse> =
        CourseId.fromString(courseId).let { id ->
            val course = courseRepository.find(id)
                course.fold(
                onSuccess = { Result.success(CourseResponse.fromCourse(it)) },
                onFailure = { Result.failure(it) }
            )
        }
}

data class CourseResponse(val id: String, val name: String, val createdAt: LocalDateTime) {
    companion object {
        fun fromCourse(course: Course) = with(course) {
            CourseResponse(
                id = id.value.toString(),
                name = name.value,
                createdAt = createdAt
            )
        }
    }
}

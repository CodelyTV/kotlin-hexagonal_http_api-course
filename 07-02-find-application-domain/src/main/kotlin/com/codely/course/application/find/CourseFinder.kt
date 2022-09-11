package com.codely.course.application.find

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseNotFoundException
import com.codely.course.domain.course.CourseRepository
import java.time.LocalDateTime

class CourseFinder(private val courseRepository: CourseRepository) {
    fun execute(courseId: String) =
        CourseId.fromString(courseId).let { id ->
            courseRepository.find(id)?.let {
                CourseResponse.fromCourse(it)
            } ?: throw CourseNotFoundException(id)
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

package com.codely.course.application.find

import com.codely.common.Either
import com.codely.common.Left
import com.codely.common.Right
import com.codely.course.domain.Course
import com.codely.course.domain.CourseError
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseRepository
import java.time.LocalDateTime

class CourseFinder(private val courseRepository: CourseRepository) {
    fun execute(courseId: String): Either<CourseError, CourseResponse> =
        CourseId.fromString(courseId).let { id ->
            courseRepository.find(id).fold(
                ifRight = { Right(CourseResponse.fromCourse(it)) },
                ifLeft = { Left(it) }
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

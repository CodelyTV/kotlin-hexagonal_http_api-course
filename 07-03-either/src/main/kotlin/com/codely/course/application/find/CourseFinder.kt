package com.codely.course.application.find

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseError
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseRepository
import com.codely.shared.common.Either
import com.codely.shared.common.Left
import com.codely.shared.common.Right
import java.time.LocalDateTime

class CourseFinder(private val courseRepository: CourseRepository) {
    fun execute(courseId: String): Either<CourseError, CourseResponse> =
        CourseId.fromString(courseId).let { id ->
            courseRepository.find(id).flatMap(
                rightF = { Right(CourseResponse.fromCourse(it)) },
                leftF = { Left(it) }
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

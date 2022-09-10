package com.codely.course.domain.course

import com.codely.shared.common.Either

interface CourseRepository {
    fun save(course: Course)
    fun find(id: CourseId): Either<CourseError, Course>
}

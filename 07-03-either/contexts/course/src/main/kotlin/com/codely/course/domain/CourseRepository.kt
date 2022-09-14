package com.codely.course.domain

import com.codely.common.Either

interface CourseRepository {
    fun save(course: Course)
    fun find(id: CourseId): Either<CourseError, Course>
}

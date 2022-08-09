package com.codely.api.domain.course

interface CourseRepository {
    fun save(course: Course)
}

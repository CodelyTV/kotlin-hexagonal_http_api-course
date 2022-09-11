package com.codely.course.domain.course

interface CourseRepository {
    fun save(course: Course)
    fun find(id: CourseId): Result<Course>
}

package com.codely.course.domain

interface CourseRepository {
    fun save(course: Course)
    fun find(id: CourseId): Course?
}

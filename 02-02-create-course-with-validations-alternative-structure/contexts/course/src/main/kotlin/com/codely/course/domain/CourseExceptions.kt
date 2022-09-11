package com.codely.course.domain

sealed class CourseException(override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)

data class InvalidCourseIdException(val id: String, override val cause: Throwable?) : CourseException("The id <$id> is not a valid course id", cause)
data class InvalidCourseNameException(val name: String) : CourseException("The name <$name> is not a valid course name")

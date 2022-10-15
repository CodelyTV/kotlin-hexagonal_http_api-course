package com.codely.course.domain

sealed class InvalidArgumentCourseException(override val message: String, override val cause: Throwable? = null) : IllegalArgumentException(message, cause)

data class InvalidCourseIdException(val id: String, override val cause: Throwable?) : InvalidArgumentCourseException("The id <$id> is not a valid course id", cause)
data class InvalidCourseNameException(val name: String) : InvalidArgumentCourseException("The name <$name> is not a valid course name")

sealed class CourseException(override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)
data class CourseNotFoundException(val id: CourseId) : CourseException("There is no course with id <${id.value}>")

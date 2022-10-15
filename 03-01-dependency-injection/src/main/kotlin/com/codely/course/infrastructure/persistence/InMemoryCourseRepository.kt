package com.codely.course.infrastructure.persistence

<<<<<<< Updated upstream:03-01-dependency-injection/src/main/kotlin/com/codely/course/infrastructure/persistence/InMemoryCourseRepository.kt
import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseRepository
=======
import com.codely.course.domain.Course
import com.codely.course.domain.CourseRepository
import org.springframework.stereotype.Component
>>>>>>> Stashed changes:03-01-dependency-injection/contexts/course/src/main/kotlin/com/codely/course/infrastructure/persistence/InMemoryCourseRepository.kt

class InMemoryCourseRepository() : CourseRepository {
    override fun save(course: Course) {
        TODO("Not yet implemented")
    }
}

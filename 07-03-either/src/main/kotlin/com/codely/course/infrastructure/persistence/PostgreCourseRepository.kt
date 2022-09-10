package com.codely.course.infrastructure.persistence

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseCannotBeFoundError
import com.codely.course.domain.course.CourseError
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import com.codely.course.domain.course.CourseNotFoundError
import com.codely.course.domain.course.CourseRepository
import com.codely.shared.common.Either
import com.codely.shared.common.Left
import com.codely.shared.common.Right
import java.sql.ResultSet
import java.util.UUID
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class PostgreCourseRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : CourseRepository {

    override fun save(course: Course) {
        MapSqlParameterSource()
            .addValue("id", course.id.value.toString())
            .addValue("name", course.name.value)
            .addValue("createdAt", course.createdAt)
            .let { params ->
                jdbcTemplate.update(
                    "INSERT INTO course (id, name, created_at) VALUES (:id,:name,:createdAt)",
                    params
                )
            }
    }

    override fun find(id: CourseId): Either<CourseError, Course> = try {
        val query = "SELECT * FROM course where id=:id"
        val params = MapSqlParameterSource().addValue("id", id.value.toString())
        jdbcTemplate.queryForObject(query, params, mapRow())
            ?.let { Right(it) }
            ?: Left(CourseNotFoundError(id))
    } catch (exception: Throwable) {
        Left(CourseCannotBeFoundError(id))
    }

    private fun mapRow(): RowMapper<Course> {
        return RowMapper { rs: ResultSet, _: Int ->
            val id = CourseId(UUID.fromString(rs.getString("id")))
            val name = CourseName(rs.getString("name"))
            val createdAt = rs.getTimestamp("created_at").toLocalDateTime()
            Course(id, name, createdAt)
        }
    }
}

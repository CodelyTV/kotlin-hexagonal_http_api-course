package com.codely.course.infrastructure.persistence

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import com.codely.shared.Application
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.sql.ResultSet
import java.sql.Types
import java.time.LocalDateTime
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@DataJpaTest
@ContextConfiguration(classes = [Application::class])
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostgreCourseRepositoryTest {

    @Autowired
    private lateinit var repository: PostgreCourseRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate;


    @Autowired
    private lateinit var strategy: FlywayMigrationStrategy


    @Test
    fun `should save a course`() {
        val courseToSave = Course(CourseId.fromString("13590efb-c181-4c5f-9f95-b768abde13e2"), CourseName("Test"), LocalDateTime.now())
        repository.save(courseToSave)

        val courseFromDb = jdbcTemplate.queryForObject(
            "select * from course where id=?",
            mapRow(),
            "13590efb-c181-4c5f-9f95-b768abde13e2"
        )

        assertEquals(courseToSave, courseFromDb)
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

data class CourseTest(
    val id: String,
    val name: String,
    val createdAt: LocalDateTime
)

@TestConfiguration
class FlywayMigrationConfig {
    @Bean
    fun cleanMigrateStrategy(): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway: Flyway ->
            flyway.clean()
            flyway.migrate()
        }
    }
}


package com.codely.shared.persistence

import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer

@ActiveProfiles("test")
open class BaseIntegrationTest {

    init {
        postgresContainer.start()
    }
    companion object {
        @JvmStatic
        val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14.5")
            .withDatabaseName("course_database")
            .withUsername("course_username")
            .withPassword("course_password")
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}

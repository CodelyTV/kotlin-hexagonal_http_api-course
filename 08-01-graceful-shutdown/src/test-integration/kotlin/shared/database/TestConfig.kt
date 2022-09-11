package shared.database

import org.springframework.context.annotation.Bean

class TestConfig {

    @Bean
    fun postgresTestUtils() = PostgresTestUtils()
}

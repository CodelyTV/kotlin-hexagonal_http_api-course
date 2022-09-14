package com.codely.shared.acceptance

import io.mockk.unmockkAll
import java.io.File
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import org.junit.jupiter.api.AfterEach
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Testcontainers
class BaseAcceptanceTest {

    companion object {
        private const val POSTGRES_PORT = 5432
        val environment: DockerComposeContainer<*> =
            DockerComposeContainer(File("docker-compose-test.yml"))
                .withExposedService("db", POSTGRES_PORT, Wait.forListeningPort())
                .withLocalCompose(true)
    }

    @PostConstruct
    fun start() {
        environment.start()
    }

    @PreDestroy
    fun stop() {
        environment.stop()
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}

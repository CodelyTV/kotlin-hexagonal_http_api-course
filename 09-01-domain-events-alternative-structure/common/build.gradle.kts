import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.diffplug.spotless")
    kotlin("plugin.spring")
}

group = "com.codely.common"

repositories {
    mavenCentral()
}

dependencies {

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.9.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
spotless {
    kotlin {
        ktlint()
            .userData(
                mapOf(
                    "insert_final_newline" to "true"
                )
            )
    }
    kotlinGradle {
        ktlint()
    }
}

tasks.check {
    dependsOn(tasks.spotlessCheck)
}

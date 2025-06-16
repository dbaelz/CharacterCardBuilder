plugins {
    kotlin("jvm") version "2.1.20"
    application
}

group = "de.dbaelz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
    implementation(libs.koog.agents)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    // Define the main class for the application.
    mainClass = "de.dbaelz.ccb.MainKt"
}
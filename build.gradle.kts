plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "MainKt"
}

dependencies {
    compileOnly("org.slf4j:slf4j-api:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    implementation(platform("io.opentelemetry:opentelemetry-bom:1.37.0"))
    implementation("io.opentelemetry:opentelemetry-api")

    // OpenTelemetry core
    implementation("io.opentelemetry:opentelemetry-sdk")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp")
    implementation("io.opentelemetry.semconv:opentelemetry-semconv")

    implementation("io.opentelemetry.instrumentation:opentelemetry-logback-appender-1.0:2.3.0-alpha")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
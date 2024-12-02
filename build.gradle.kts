import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "io.github.mikaojk"
version = "1.0.0"

val javaVersion = JvmTarget.JVM_21

val kotlinVersion = "2.1.0"

plugins {
    kotlin("jvm") version "2.1.0"
    id("application")
}

application {
    mainClass.set("io.github.mikaojk.MainKt")
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget = javaVersion
        }
    }
}
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.22"
    alias(libs.plugins.sonatypeCentralPortalPublisher)
}

allprojects {
    group = "world.avionik"
    version = "1.0.3"

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.github.johnrengelman.shadow")
    }

    repositories {
        mavenCentral()

        // jitpack repositories
        maven("https://jitpack.io/")

        // minecraft repositories
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.loohpjames.com/repository/")

        // simplecloud repository
        maven("https://repo.thesimplecloud.eu/artifactory/list/gradle-release-local/")
    }
}

subprojects {
    dependencies {
        compileOnly(kotlin("stdlib"))

        // database dependencies
        api("redis.clients:jedis:5.1.0")
        api("com.arangodb:arangodb-java-driver:7.6.0")
        api("dev.morphia.morphia:morphia-core:2.3.2")
    }

    tasks.named("shadowJar", ShadowJar::class) {
        mergeServiceFiles()
    }
}
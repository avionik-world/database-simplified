pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "database-simplified"
include("database-simplified-kit")
include("database-simplified-plugin")
include("database-simplified-plugin:plugin-velocity")
findProject(":database-simplified-plugin:plugin-velocity")?.name = "plugin-velocity"
include("database-simplified-plugin:plugin-paper")
findProject(":database-simplified-plugin:plugin-paper")?.name = "plugin-paper"
include("database-simplified-plugin:plugin-shared")
findProject(":database-simplified-plugin:plugin-shared")?.name = "plugin-shared"
include("database-simplified-plugin:plugin-limbo")
findProject(":database-simplified-plugin:plugin-limbo")?.name = "plugin-limbo"

plugins {
    kotlin("plugin.serialization") version "1.9.0"
}

dependencies {
    compileOnly("world.avionik:config-kit:1.0.1")
    compileOnly("eu.thesimplecloud.jsonlib:json-lib:1.0.10")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/avionik-world/database-simplified")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
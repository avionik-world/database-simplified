plugins {
    kotlin("plugin.serialization") version "1.9.0"
    alias(libs.plugins.sonatypeCentralPortalPublisher)
}

dependencies {
    // avionik dependencies
    compileOnly("world.avionik:config-kit:1.0.2")

    // simplecloud dependencies
    compileOnly("eu.thesimplecloud.jsonlib:json-lib:1.0.10")
}

signing {
    useGpgCmd()
    sign(configurations.archives.get())
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

centralPortal {
    username = project.findProperty("sonatypeUsername") as String
    password = project.findProperty("sonatypePassword") as String

    pom {
        name.set("Database Simplified")
        description.set("Simplified Morphia and Jedis integration")
        url.set("https://github.com/avionik-world/database-simplified")

        developers {
            developer {
                id.set("niklasnieberler")
                email.set("admin@avionik.world")
            }
        }
        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        scm {
            url.set("https://github.com/avionik-world/database-simplified.git")
            connection.set("git:git@github.com:avionik-world/database-simplified.git")
        }
    }
}
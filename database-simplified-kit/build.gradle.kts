plugins {
    kotlin("plugin.serialization") version "1.9.0"
    alias(libs.plugins.sonatypeCentralPortalPublisher)
}

dependencies {
    // avionik dependencies
    compileOnly("world.avionik:config-kit:1.0.2")

    // kubernetes dependencies
    compileOnly("io.fabric8:kubernetes-client:6.12.1")

    // simplecloud dependencies
    compileOnly("eu.thesimplecloud.jsonlib:json-lib:1.0.10")
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

centralPortal {
    name = "database-simplified-kit"

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
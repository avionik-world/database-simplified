plugins {
    kotlin("plugin.serialization") version "1.9.0"
}

dependencies {
    // avionik dependencies
    compileOnly("world.avionik:config-kit:1.0.2")

    // database-simplified dependencies
    api(project(":database-simplified-kit"))
}
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    // database-simplified dependencies
    api(project(":database-simplified-plugin:plugin-shared"))

    // paper dependencies
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
}

tasks.named("shadowJar", ShadowJar::class) {
    archiveFileName = "database-simplified-spigot-${project.version}.jar"
}
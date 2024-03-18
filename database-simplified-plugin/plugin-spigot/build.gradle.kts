import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    // database-simplified dependencies
    api(project(":database-simplified-plugin:plugin-shared"))

    // spigot dependencies
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
}

tasks.named("shadowJar", ShadowJar::class) {
    archiveFileName = "database-simplified-spigot-${project.version}.jar"
}
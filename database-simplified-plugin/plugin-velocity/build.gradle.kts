import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    // database-simplified dependencies
    api(project(":database-simplified-plugin:plugin-shared"))

    // velocity dependencies
    compileOnly("com.velocitypowered:velocity-api:3.1.1")
    annotationProcessor("com.velocitypowered:velocity-api:3.1.1")
}

tasks.named("shadowJar", ShadowJar::class) {
    archiveFileName = "database-simplified-velocity-${project.version}.jar"
}
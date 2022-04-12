plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.jonakls"
version = "1.0"


repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    implementation(project(":api"))

    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("net.md-5:bungeecord-chat:1.8-SNAPSHOT")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        artifactId = "kalea-text-bukkit"
    }
}

tasks {
    shadowJar {
        archiveBaseName.set("kalea-text-bukkit")
        minimize()
    }
}

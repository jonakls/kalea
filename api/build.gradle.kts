plugins {
    java
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.codemc.org/repository/maven-public/")
}


dependencies {
    implementation("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
}


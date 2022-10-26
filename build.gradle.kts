plugins {
    kotlin("jvm") version "1.7.20"
    id("fabric-loom") version "1.0-SNAPSHOT"
    `maven-publish`
}

group = property("maven_group")!!
version = property("mod_version")!!

repositories {
    maven(url = "https://maven.shedaniel.me")
    maven(url = "https://maven.architectury.dev")
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft")}")
    mappings("net.fabricmc:yarn:${property("yarn")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api")}")
    // https://linkie.shedaniel.me/dependencies
    modRuntimeOnly("me.shedaniel:RoughlyEnoughItems-fabric:9.1.562")
    modRuntimeOnly("dev.architectury:architectury-fabric:6.3.49")
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }
    jar {
        from("LICENSE")
    }
    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifact(remapJar) {
                    builtBy(remapJar)
                }
                artifact(kotlinSourcesJar) {
                    builtBy(remapSourcesJar)
                }
            }
        }
        repositories {
            // mavenLocal()
        }
    }
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

java {
    withSourcesJar()
}

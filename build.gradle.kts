plugins {
    kotlin("jvm") version "1.7.20"
    id("fabric-loom") version "1.0-SNAPSHOT"
    `maven-publish`
    id("net.thauvin.erik.gradle.semver") version "1.0.4"
}
semver {
    properties = "version.properties"
    semverKey = "version"
    preReleaseKey = "release"
    buildMetaKey = "meta"
}

group = property("maven_group")!!
version = semver.version

repositories {
    // https://www.cursemaven.com
    maven(url = "https://www.cursemaven.com")
    // https://linkie.shedaniel.me/dependencies
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
    modRuntimeOnly("dev.architectury:architectury-fabric:6.3.49")
    modRuntimeOnly("me.shedaniel:RoughlyEnoughItems-fabric:9.1.572")
    // https://www.curseforge.com/minecraft/mc-mods/jade/files
    modRuntimeOnly("curse.maven:jade-324717:4054977")
}

tasks {
    build {
        doLast {
            println("Build: ${project.name}-${project.version}.jar")
        }
    }
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

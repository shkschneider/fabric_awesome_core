plugins {
    kotlin("jvm") version "1.7.20"
    id("fabric-loom") version "1.0.10"
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
    maven(url = "https://maven.terraformersmc.com") { name = "terraformers" }
    maven(url = "https://www.cursemaven.com") { name = "curse" }
    maven(url = "https://maven.shedaniel.me") { name = "shedaniel" }
    maven(url = "https://maven.architectury.dev") { name = "architectury" }
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft")}")
    mappings("net.fabricmc:yarn:${property("yarn")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api")}")
    // https://maven.terraformersmc.com/dev/emi/emi
    modImplementation("dev.emi:emi:0.4.2+1.19") { exclude(group = "net.fabricmc.fabric-api") }
    // https://linkie.shedaniel.me/dependencies
    modImplementation("dev.architectury:architectury-fabric:6.3.49") { exclude(group = "net.fabricmc.fabric-api") }
    modImplementation("me.shedaniel:RoughlyEnoughItems-fabric:9.1.572") { exclude(group = "net.fabricmc.fabric-api") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:9.1.572") { exclude(group = "net.fabricmc.fabric-api") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-fabric:9.1.572") { exclude(group = "net.fabricmc.fabric-api") }
    // https://www.curseforge.com/minecraft/mc-mods/jade/files
    modRuntimeOnly("curse.maven:jade-324717:4054977") { exclude(group = "net.fabricmc.fabric-api") }
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

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
java {
    withSourcesJar()
}

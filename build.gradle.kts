plugins {
    kotlin("jvm") version "1.7.20"
    id("fabric-loom") version "1.0.10"
    id("net.thauvin.erik.gradle.semver") version "1.0.4"
}
semver.properties = "${rootDir}/version.properties"

repositories {
    maven(url = "https://maven.terraformersmc.com") { name = "terraformers" }
    maven(url = "https://www.cursemaven.com") { name = "curse" }
    maven(url = "https://maven.shedaniel.me") { name = "shedaniel" }
    maven(url = "https://maven.architectury.dev") { name = "architectury" }
}

dependencies {
    implementation(project(path = ":core", configuration = "namedElements"))

    // https://maven.terraformersmc.com/dev/emi/emi
    modImplementation("dev.emi:emi:0.4.2+1.19") { exclude(group = "net.fabricmc") }
    // https://linkie.shedaniel.me/dependencies
    modCompileOnly("dev.architectury:architectury-fabric:6.3.49") { exclude(group = "net.fabricmc") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-fabric:9.1.572") { exclude(group = "net.fabricmc") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:9.1.572") { exclude(group = "net.fabricmc") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-fabric:9.1.572") { exclude(group = "net.fabricmc") }
    // https://www.curseforge.com/minecraft/mc-mods/jade/files
    modRuntimeOnly("curse.maven:jade-324717:4054977") { exclude(group = "net.fabricmc") }
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "fabric-loom")
    group = property("maven_group").toString()
    version = rootProject.version
    dependencies {
        minecraft("com.mojang:minecraft:${property("minecraft")}")
        mappings("net.fabricmc:yarn:${property("yarn")}:v2")
        modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader")}")
        modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin")}")
        modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api")}")
    }
    tasks {
        processResources {
            inputs.property("version", rootProject.version)
            filesMatching("fabric.mod.json") {
                expand(mutableMapOf("version" to rootProject.version))
            }
        }
        build {
            doLast {
                if (project.name == rootProject.name) {
                    delete("${rootProject.buildDir}/libs/${rootProject.name}-${rootProject.version}.jar")
                } else {
                    val jar = "${rootProject.name}-${project.name}-${rootProject.version}.jar"
                    copy {
                        from("${project.buildDir}/libs/")
                        include("${project.name}.jar")
                        into("${rootProject.buildDir}/libs/")
                        rename("${project.name}.jar", jar)
                    }
                    println("Output: $jar")
                }
            }
        }
        jar {
            from("LICENSE")
        }
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
        compileKotlin {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

plugins {
    // https://github.com/JetBrains/kotlin/releases
    kotlin("jvm") version "1.7.21"
    // https://maven.fabricmc.net/fabric-loom/fabric-loom.gradle.plugin/
    id("fabric-loom") version "1.0.12"
}

fun version(): String {
    val bytes = org.apache.commons.io.output.ByteArrayOutputStream()
    project.exec {
        commandLine = "git describe --tags HEAD".split(" ")
        standardOutput = bytes
    }
    return String(bytes.toByteArray()).trim()
}
group = "io.github.shkschneider"
version = version()

repositories {
    maven(url = "https://www.cursemaven.com") { name = "curse" }
}

dependencies {
    subprojects.forEach { subproject ->
        implementation(project(path = ":${subproject.name}", configuration = "namedElements"))
    }
    // https://maven.terraformersmc.com/dev/emi/emi
    modRuntimeOnly("curse.maven:emi-580555:4077428") { exclude(group = "net.fabricmc") }
    // https://www.curseforge.com/minecraft/mc-mods/jade/files
    modRuntimeOnly("curse.maven:jade-324717:4054977") { exclude(group = "net.fabricmc") }
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "fabric-loom")
    group = rootProject.group
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
                    val oldJar = "${project.name}-${rootProject.version}.jar"
                    if (File("${project.buildDir}/libs/$oldJar").exists()) {
                        val newJar = "${rootProject.name}-${project.name}-${rootProject.version}.jar"
                        copy {
                            from("${project.buildDir}/libs/")
                            include(oldJar)
                            into("${rootProject.buildDir}/libs/")
                            rename(oldJar, newJar)
                        }
                        println("Output: $newJar")
                    } else {
                        throw IllegalStateException("${project.name}/libs/$oldJar not found!")
                    }
                }
            }
        }
        jar {
            from("LICENSE")
        }
        withType<JavaCompile> {
            options.release.set(JavaVersion.VERSION_17.toString().toInt())
            options.encoding = "UTF-8"
        }
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

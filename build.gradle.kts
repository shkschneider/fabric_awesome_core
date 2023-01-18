import groovy.json.JsonSlurper
import org.apache.commons.io.output.ByteArrayOutputStream
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

plugins {
    // https://github.com/JetBrains/kotlin/releases
    kotlin("jvm") version "1.8.0"
    // https://maven.fabricmc.net/fabric-loom/fabric-loom.gradle.plugin/
    id("fabric-loom") version "1.0.17"
}

fun version(): String {
    val bytes = ByteArrayOutputStream()
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
    // runtime mods (for development only)
    listOf(
        // projectId to fileId
        "emi-580555" to "4337339", // https://www.curseforge.com/minecraft/mc-mods/emi/files
        "jade-324717" to "4328555", // https://www.curseforge.com/minecraft/mc-mods/jade/files
        // FIXME Xaero's Minimap (1.19.3_22.17.1_fabric) has crashed!
        // "xaeros-minimap-263420" to "4338624", // https://www.curseforge.com/minecraft/mc-mods/xaeros-minimap/files
    ).forEach { mod ->
        modRuntimeOnly("curse.maven:${mod.first}:${mod.second}") { exclude(group = "net.fabricmc") }
    }
}

tasks {
    register("versions") {
        println()
        println("> Versions")
        @Suppress("UNCHECKED_CAST")
        fun URL.versionsFromJson(): List<Pair<String, Boolean>> =
            (JsonSlurper().parse(this) as ArrayList<Map<String, Any>>).map { it.entries }.map { entry ->
                (entry.first { it.key == "version" }.value as String) to (entry.first { it.key == "stable" }.value as Boolean)
            }
        fun URL.versionsFromXml(): List<String> =
            BufferedReader(InputStreamReader(this.content as InputStream))
                .readLines().filterNot { it.contains("-pre", ignoreCase = true) || it.contains("-snapshot", ignoreCase = true) }
                .filter { it.contains("<version>") }.map { it.replace("</?version>".toRegex(), "").trim() }
        fun report(name: String, latest: String) {
            rootProject.property(name).takeIf { it.toString().contains(" ").not() }?.let { current ->
                if (current == latest) {
                    println("- $name: $current")
                } else {
                    println("- $name: $current -> $latest")
                }
            } ?: run {
                println("- $name: ? -> $latest")
            }
        }
        val mc = URL("https://meta.fabricmc.net/v2/versions/game").versionsFromJson().first { it.second }.first
        val kvm = URL("https://maven.fabricmc.net/net/fabricmc/fabric-language-kotlin/maven-metadata.xml").versionsFromXml().last()
        report("kotlin", kvm.split("+").last().replace("kotlin.", ""))
        report("loom", URL("https://maven.fabricmc.net/fabric-loom/fabric-loom.gradle.plugin/maven-metadata.xml").versionsFromXml().last())
        report("minecraft", mc)
        report("yarn", URL("https://meta.fabricmc.net/v2/versions/yarn/$mc").versionsFromJson().first().first)
        report("fabric_loader", URL("https://meta.fabricmc.net/v2/versions/loader").versionsFromJson().first { it.second }.first)
        report("fabric_kotlin", kvm)
        report("fabric_api", URL("https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/maven-metadata.xml").versionsFromXml().last { it.contains(mc) })
    }
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "fabric-loom")
    group = rootProject.group
    version = rootProject.version
    sourceSets.main {
        java {
            setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
        }
    }
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
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

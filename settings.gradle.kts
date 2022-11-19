rootProject.name = "Awesome"

println()
println("> Modules")
rootDir.listFiles { dir, _ -> dir.isDirectory }
    .filter { file -> File("$file/build.gradle.kts").exists() }
    .forEach { module ->
        println(":${module.name}")
        include(":${module.name}")
    }

pluginManagement {
    repositories {
        maven(url = "https://plugins.gradle.org/m2") { name = "gradle" }
        maven(url = "https://libraries.minecraft.net") { name = "minecraft" }
        maven(url = "https://maven.fabricmc.net") { name = "fabric" }
        mavenCentral()
        gradlePluginPortal()
    }
}

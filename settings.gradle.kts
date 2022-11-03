rootProject.name = "Awesome"
pluginManagement {
    repositories {
        maven("https://plugins.gradle.org/m2") { name = "gradle" }
        maven("https://libraries.minecraft.net") { name = "minecraft" }
        maven("https://maven.fabricmc.net") { name = "fabric" }
        mavenCentral()
        gradlePluginPortal()
    }
}

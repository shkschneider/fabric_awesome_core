repositories {
    maven(url = "https://maven.terraformersmc.com") { name = "terraformers" }
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
}

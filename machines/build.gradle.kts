repositories {
    maven(url = "https://maven.terraformersmc.com") { name = "terraformers" }
    maven(url = "https://maven.architectury.dev") { name = "architectury" }
    maven(url = "https://maven.shedaniel.me") { name = "shedaniel" }
}

dependencies {
    implementation(project(path = ":core", configuration = "namedElements"))
    // https://maven.terraformersmc.com/dev/emi/emi
    modImplementation("dev.emi:emi:0.6.1+1.19.3") { exclude(group = "net.fabricmc") }
    // https://linkie.shedaniel.me/dependencies
    modCompileOnly("dev.architectury:architectury-fabric:7.0.66") { exclude(group = "net.fabricmc") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-fabric:10.0.581") { exclude(group = "net.fabricmc") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:10.0.581") { exclude(group = "net.fabricmc") }
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-fabric:10.0.581") { exclude(group = "net.fabricmc") }
}

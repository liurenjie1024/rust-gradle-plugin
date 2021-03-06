import io.github.liurenjie1024.gradle.rust.FeatureSpec as CargoFeatureSpec
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.github.liurenjie1024.gradle.rust:rust-gradle-plugin:0.1.0")
    }
}

apply(plugin = "io.github.liurenjie1024.gradle.rust")

configure<io.github.liurenjie1024.gradle.rust.CargoExtension> {
    cargoCommand.set("cargo")
}

tasks.withType(io.github.liurenjie1024.gradle.rust.CargoBuildTask::class.java).configureEach {
    verbose = false
    release = true
    featureSpec = CargoFeatureSpec.all()
}

tasks.withType(io.github.liurenjie1024.gradle.rust.CargoCleanTask::class.java).configureEach {
    verbose = false
}


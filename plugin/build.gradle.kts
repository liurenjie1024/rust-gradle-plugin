import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.4.10"
    id("com.gradle.plugin-publish") version "0.12.0"
}

repositories {
    mavenCentral()
}

group = "io.github.liurenjie1024.gradle.rust"
version = "0.1.0"

gradlePlugin {
    plugins {
        create("rustPlugin") {
            id = "io.github.liurenjie1024.gradle.rust"
            displayName = "Rust Plugin"
            description = "Gradle plugin to help integrate rust project into gralde project"
            implementationClass = "io.github.liurenjie1024.gradle.rust.RustPlugin"
        }
    }
}

dependencies {
    implementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.12")
}



tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

pluginBundle {
    website = "https://github.com/liurenjie1024/rust-gradle-plugin"
    vcsUrl = "https://github.com/liurenjie1024/rust-gradle-plugin"
    tags = listOf("rust", "rust-lang")
}

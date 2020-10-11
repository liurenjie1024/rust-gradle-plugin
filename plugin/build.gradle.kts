import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.4.10"

}

repositories {
    mavenCentral()
}

group = "io.github.liurenjie1024.gradle.rust"
version = "0.0.1-SNAPSHOT"

gradlePlugin {
    plugins {
        create("rustPlugin") {
            id = "io.github.liurenjie1024.gradle.rust"
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

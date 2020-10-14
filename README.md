Rust Gradle Plugin

![CI](https://github.com/liurenjie1024/rust-gradle-plugin/workflows/CI/badge.svg?branch=master)

This plugin help to integrate rust project into gradle projects.

# Usage

In your *project's* build.gradle.kts, apply plugin and add the `cargo` configuration:

```kotlin
import io.github.liurenjie1024.gradle.rust.FeatureSpec as CargoFeatureSpec

plugins {
    id("io.github.liurenjie1024.gradle.rust") version "<latest version>"
}

configure<io.github.liurenjie1024.gradle.rust.CargoExtension> {
    cargoCommand.set("cargo") // This is optional as default cargo command is cargo
}
```

This has already inserted `cargoBuild`, `cargoClean`, `cargoDoc`, `cargoTest` commands into gradle's life cycle tasks, 
so jut run `build` to compile rust project.
```sh
./gradlew build
```

## Configuration


### `cargoBuild`

`cargoBuild` invokes `cargo build` command, and it has been added as a dependency of build task. It can be configured by 
providing extra arguments, which will be passed to `cargo build` command.

```kotlin
tasks.withType(io.github.liurenjie1024.gradle.rust.CargoBuildTask::class.java).configureEach {
    extraArguments = listOf("--release")
}
```

`extraArguments` will be passed to `cargo build` command.

### `cargoClean`

`cargoClean` invokes `cargo clean` command, and it has been added as a dependency of clean task. It can be configured by 
providing extra arguments, which will be passed to `cargo clean` command.

```kotlin
tasks.withType(io.github.liurenjie1024.gradle.rust.CargoCleanTask::class.java).configureEach {
    extraArguments = listOf("--release")
}
```

### `cargoTest`

`cargoTest` invokes `cargo test` command, and it has been added as a dependency of verification task. It can be configured by 
providing extra arguments, which will be passed to `cargo test` command.

```kotlin
tasks.withType(io.github.liurenjie1024.gradle.rust.CargoTestTask::class.java).configureEach {
    extraArguments = listOf("--release")
}
```

### `cargoDoc`

`cargoDoc` invokes `cargo rustdoc` command, and it can be invoked by running `./gradlew cargoDoc`. It can be configured 
by providing extra arguments, which will be passed to `cargo rustdoc` command.
                                                                                                   
 ```kotlin
tasks.withType(io.github.liurenjie1024.gradle.rust.CargoDocTask::class.java).configureEach {
    extraArguments = listOf("--release")
}
```



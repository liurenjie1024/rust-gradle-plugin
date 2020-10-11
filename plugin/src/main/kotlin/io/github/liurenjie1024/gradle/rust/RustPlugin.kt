package io.github.liurenjie1024.gradle.rust

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

const val DEFAULT_CARGO_COMMAND: String = "cargo"

open class RustPlugin : Plugin<Project> {
    lateinit var cargoExtension: CargoExtension

    override fun apply(project: Project) {
        with(project) {
            // Apply base plugin
            project.pluginManager.apply(BasePlugin::class.java)
            cargoExtension = extensions.create("cargo", CargoExtension::class.java)

            // Create cargo build task
            val cargoBuildTask = tasks.create("cargoBuild", CargoBuildTask::class.java).apply {
                group = "build"
                description = "Run cargo build command"
                cargoCommand = cargoExtension.cargoCommand
            }
            tasks.getByPath("build").dependsOn(cargoBuildTask)
        }
    }
}
package io.github.liurenjie1024.gradle.rust

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

const val DEFAULT_CARGO_COMMAND: String = "cargo"

open class RustPlugin : Plugin<Project> {
    private lateinit var cargoExtension: CargoExtension

    override fun apply(project: Project) {
        with(project) {
            // Apply base plugin
            project.pluginManager.apply(BasePlugin::class.java)
            cargoExtension = extensions.create(CargoExtension.NAME, CargoExtension::class.java)

            // Create cargo build task
            val cargoBuildTask = tasks.create(CargoBuildTask.NAME, CargoBuildTask::class.java).apply {
                group = "build"
                description = "Run cargo build command"
                cargoCommand.set(cargoExtension.cargoCommand)
                // Disable user change
                cargoCommand.finalizeValue()
            }
            tasks.getByPath("build").dependsOn(cargoBuildTask)
        }
    }
}
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

            createCargoBuildTask(project)
            createCargoCleanTask(project)
        }
    }

    private fun createCargoBuildTask(project: Project) {
        with(project) {
            // Create cargo build task
            val cargoBuildTask = tasks.create(CargoBuildTask.NAME, CargoBuildTask::class.java).apply {
                group = "build"
                description = "Run cargo build command"
                cargoCommand.set(cargoExtension.cargoCommand)
            }
            tasks.getByPath("build").dependsOn(cargoBuildTask)
        }
    }

    private fun createCargoCleanTask(project: Project) {
        with(project) {
            // Create cargo build task
            val cargoCleanTask = tasks.create(CargoCleanTask.NAME, CargoCleanTask::class.java).apply {
                group = "build"
                description = "Run cargo clean command"
                cargoCommand.set(cargoExtension.cargoCommand)
            }
            tasks.getByPath("clean").dependsOn(cargoCleanTask)
        }
    }
}
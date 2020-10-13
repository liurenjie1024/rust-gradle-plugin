package io.github.liurenjie1024.gradle.rust

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

const val PLUGIN_ID: String = "io.github.liurenjie1024.gradle.rust"

class RustPluginTest {
    private lateinit var project: Project

    @Before
    fun setupTest() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply(PLUGIN_ID)

        project.extensions.configure(CargoExtension::class.java) {
            it.cargoCommand.set("cargo2")
        }
    }

    @Test
    fun checkCargoBuildTask() {
        val task = project.tasks.getByName(CargoBuildTask.NAME)
        assertTrue(task is CargoBuildTask)

        task.apply {
            verbose = true
            release = true
            extraCargoBuildArguments = listOf("extra")
            featureSpec = FeatureSpec.all()
        }

        assertEquals(listOf("cargo2", "build", "--verbose", "--all-features", "--release", "extra"), task.buildCommandLine())
    }

    @Test
    fun checkCargoCleanTask() {
        val task = project.tasks.getByName(CargoCleanTask.NAME)
        assertTrue(task is CargoCleanTask)

        task.apply {
            verbose = true
            extraCargoBuildArguments = listOf("extra")
        }

        assertEquals(listOf("cargo2", "clean", "--verbose", "extra"), task.buildCommandLine())
    }
}
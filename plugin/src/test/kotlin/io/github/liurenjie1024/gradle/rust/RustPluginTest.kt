package io.github.liurenjie1024.gradle.rust

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

const val PLUGIN_ID: String = "io.github.liurenjie1024.gradle.rust"

class RustPluginTest {
    lateinit var project: Project

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
            release = true
        }

        assertEquals(listOf("cargo2", "build", "--release"), task.buildCommandLine())
    }
}
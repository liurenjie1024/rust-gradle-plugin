package io.github.liurenjie1024.gradle.rust

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert.*
import org.junit.Test

class CargoBuildTaskTest {
    @Test
    fun testCanCreate() {
        val project = ProjectBuilder.builder().build()
        val task = project.tasks.register("cargoBuild", CargoBuildTask::class.java).get()

        assertTrue(task is CargoBuildTask)
    }

    @Test
    fun testCommandLine() {
        val project = ProjectBuilder.builder().build()
        val task = project.tasks.register("cargoBuild", CargoBuildTask::class.java).get()
        assertEquals(listOf("cargo", "build"), task.buildCommandLine())
    }
}
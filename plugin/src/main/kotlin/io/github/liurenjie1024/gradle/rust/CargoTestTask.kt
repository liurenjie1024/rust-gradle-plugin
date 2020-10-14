package io.github.liurenjie1024.gradle.rust

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

open class CargoTestTask: DefaultTask() {
    companion object {
        const val NAME = "cargoTest"
    }

    @Input
    val cargoCommand: Property<String> = project.objects.property(String::class.java)
    @Input
    var extraArguments: List<String> = emptyList()

    @Suppress("unused")
    @TaskAction
    fun clean() {
        project.exec {
            it.commandLine = buildCommandLine()
        }.assertNormalExitValue()
    }

    fun buildCommandLine(): List<String> {
        val commandLine = mutableListOf(cargoCommand.get(), "test")

        extraArguments.let {
            commandLine.addAll(it)
        }

        return commandLine
    }
}
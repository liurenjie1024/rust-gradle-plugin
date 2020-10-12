package io.github.liurenjie1024.gradle.rust;

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction


open class CargoBuildTask: DefaultTask() {
    companion object {
        const val NAME = "cargoBuild"
    }
    @Input
    val cargoCommand: Property<String> = project.objects.property(String::class.java)
    @Input
    var release: Boolean = false
    @Input
    var verbose: Boolean = false
    @Input
    var extraCargoBuildArguments: List<String> = emptyList()
    @Input
    var featureSpec: FeatureSpec = FeatureSpec()

    @Suppress("unused")
    @TaskAction
    fun build() {
        project.exec {
            it.commandLine = buildCommandLine()
        }.assertNormalExitValue()
    }

    fun buildCommandLine(): List<String> {
        val commandLine = mutableListOf(cargoCommand.get(), "build")

        if (verbose) {
            commandLine += "--verbose"
        }

        // We just pass this along to cargo as something space separated... AFAICT
        // you're allowed to have featureSpec with spaces in them, but I don't think
        // there's a way to specify them in the cargo command line -- rustc accepts
        // them if passed in directly with `--cfg`, and cargo will pass them to rustc
        // if you use them as default featureSpec.
        when (featureSpec.type) {
            FeaturesType.All -> {
                commandLine += "--all-features"
            }
            FeaturesType.Default -> {
                if (!featureSpec.featureSet.isEmpty()) {
                    commandLine += "--features"
                    commandLine += featureSpec.featureSet.joinToString(" ")
                }
            }
            FeaturesType.NoDefault -> {
                commandLine += "--no-default-features"
                if (!featureSpec.featureSet.isEmpty()) {
                    commandLine += "--features"
                    commandLine += featureSpec.featureSet.joinToString(" ")
                }
            }
        }

        if (release) {
            // Cargo is rigid: it accepts "--release" for release (and
            // nothing for dev).  This is a cheap way of allowing only
            // two values.
            commandLine += "--release"
        }

        extraCargoBuildArguments?.let {
            commandLine.addAll(it)
        }

        return commandLine
    }
}


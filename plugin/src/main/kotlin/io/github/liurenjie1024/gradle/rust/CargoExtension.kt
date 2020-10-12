package io.github.liurenjie1024.gradle.rust

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property


enum class FeaturesType { All, Default, NoDefault}

data class FeatureSpec(val type: FeaturesType = FeaturesType.Default, val featureSet: Set<String> = emptySet()) {
    companion object {
        fun all(): FeatureSpec = FeatureSpec(type = FeaturesType.All)

        fun defaultAnd(inputFeatureSet: Array<String>) = FeatureSpec(featureSet = inputFeatureSet.toSet())

        fun noDefaultBut(featureSet: Array<String>) = FeatureSpec(type = FeaturesType.NoDefault, featureSet = featureSet.toSet())
    }
}

open class CargoExtension(objects: ObjectFactory) {
    companion object {
        const val NAME = "cargo"
    }
    val cargoCommand: Property<String> = objects.property(String::class.java).value(DEFAULT_CARGO_COMMAND)
}

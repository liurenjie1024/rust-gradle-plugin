package io.github.liurenjie1024.gradle.rust

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property


enum class FeaturesType { All, Default, NoDefault}

data class FeatureSpec private constructor(val type: FeaturesType = FeaturesType.Default, val featureSet: Set<String> = emptySet()) {
    companion object {
        fun all(): FeatureSpec = FeatureSpec(type = FeaturesType.All)

        fun defaultAnd(extraFeatures: Array<String> = emptyArray()) = FeatureSpec(featureSet = extraFeatures.toSet())

        fun noDefaultBut(features: Array<String>) = FeatureSpec(type = FeaturesType.NoDefault, featureSet = features.toSet())
    }
}

open class CargoExtension(objects: ObjectFactory) {
    companion object {
        const val NAME = "cargo"
    }
    val cargoCommand: Property<String> = objects.property(String::class.java).value(DEFAULT_CARGO_COMMAND)
}

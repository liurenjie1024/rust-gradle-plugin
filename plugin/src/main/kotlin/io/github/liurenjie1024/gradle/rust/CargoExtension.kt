package io.github.liurenjie1024.gradle.rust


enum class FeaturesType { All, Default, NoDefault}

data class FeatureSpec(val type: FeaturesType = FeaturesType.Default, val featureSet: Set<String> = emptySet()) {
    companion object {
        fun all(): FeatureSpec = FeatureSpec(type = FeaturesType.All)

        fun defaultAnd(inputFeatureSet: Array<String>) = FeatureSpec(featureSet = inputFeatureSet.toSet())

        fun noDefaultBut(featureSet: Array<String>) = FeatureSpec(type = FeaturesType.NoDefault, featureSet = featureSet.toSet())
    }
}

open class CargoExtension {
    var cargoCommand = DEFAULT_CARGO_COMMAND
}

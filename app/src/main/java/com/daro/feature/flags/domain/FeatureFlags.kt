package com.daro.feature.flags.domain

interface FeatureFlags {

    suspend fun isEnabled(feature: String): Boolean

}
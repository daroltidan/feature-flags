package com.daro.feature.flags.domain

import com.daro.feature.flags.data.Feature

interface FeatureFlags {

    suspend fun isEnabled(feature: String): Boolean

}
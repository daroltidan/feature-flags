package com.daro.feature.flags.data.sources

import com.daro.feature.flags.domain.FeatureFlags
import javax.inject.Inject

class RetrofitFeature @Inject constructor() : FeatureFlags {

    override suspend fun isEnabled(feature: String): Boolean {
        TODO("Not yet implemented")
    }
}
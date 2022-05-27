package com.daro.feature.flags.data.sources

import com.daro.feature.flags.data.retrofit.RetrofitService
import com.daro.feature.flags.domain.FeatureFlags
import javax.inject.Inject

class RetrofitFeature @Inject constructor(
    private val service: RetrofitService
) : FeatureFlags {

    override suspend fun isEnabled(feature: String): Boolean {
        val response = service.getDb()
        return response[feature] ?: false
    }
}
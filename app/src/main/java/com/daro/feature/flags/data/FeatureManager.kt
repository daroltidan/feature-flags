package com.daro.feature.flags.data

import com.daro.feature.flags.data.enties.Feature
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FeatureManager @Inject constructor(
    private val sourceFactory: SourceFactory
) {

    suspend fun isFeatureEnabled(feature: Feature): Boolean {
        val source = sourceFactory(feature.source)
        return source.isEnabled(feature.key)
    }

}
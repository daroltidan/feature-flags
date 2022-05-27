package com.daro.feature.flags.data.sources

import android.content.SharedPreferences
import com.daro.feature.flags.domain.FeatureFlags
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SharedPreferencesFeature @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : FeatureFlags {

    override suspend fun isEnabled(feature: String) = sharedPreferences.getBoolean(feature, false)

}
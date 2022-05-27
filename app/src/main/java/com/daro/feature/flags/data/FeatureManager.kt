package com.daro.feature.flags.data

import com.daro.feature.flags.di.LocalFlag
import com.daro.feature.flags.di.RemoteFlag
import com.daro.feature.flags.domain.FeatureFlags
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FeatureManager @Inject constructor(
    @RemoteFlag val remoteFlag: FeatureFlags,
    @LocalFlag val localFlag: FeatureFlags
) {

    suspend fun isFeatureEnabled(feature: Feature) = when (feature.source) {
        is Source.Remote -> remoteFlag.isEnabled(feature.key)
        is Source.Local -> localFlag.isEnabled(feature.key)
    }

}
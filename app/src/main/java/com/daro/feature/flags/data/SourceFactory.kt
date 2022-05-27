package com.daro.feature.flags.data

import com.daro.feature.flags.data.enties.Source
import com.daro.feature.flags.di.ConfigJsonFlag
import com.daro.feature.flags.di.FirebaseFlag
import com.daro.feature.flags.di.SharedPrefsFlag
import com.daro.feature.flags.domain.FeatureFlags
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SourceFactory @Inject constructor(
    @FirebaseFlag val firebaseFlag: FeatureFlags,
    @ConfigJsonFlag val configJsonFlag: FeatureFlags,
    @SharedPrefsFlag val sharedPrefsFlag: FeatureFlags
) {

    operator fun invoke(source: Source) = when (source) {
        Source.ConfigJson -> configJsonFlag
        Source.Firebase -> firebaseFlag
        Source.SharedPrefs -> sharedPrefsFlag
    }

}
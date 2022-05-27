package com.daro.feature.flags.di

import com.daro.feature.flags.data.sources.LocalJsonFeature
import com.daro.feature.flags.data.sources.RemoteFirebaseFeature
import com.daro.feature.flags.data.sources.SharedPreferencesFeature
import com.daro.feature.flags.domain.FeatureFlags
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConfigJsonFlag

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirebaseFlag

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SharedPrefsFlag

@Module
@InstallIn(ViewModelComponent::class)
abstract class FlagsModule {

    @ConfigJsonFlag
    @Binds
    abstract fun localFlags(localJsonFeature: LocalJsonFeature): FeatureFlags

    @FirebaseFlag
    @Binds
    abstract fun remoteFlags(remoteFirebaseFeature: RemoteFirebaseFeature): FeatureFlags

    @SharedPrefsFlag
    @Binds
    abstract fun sharedPrefs(sharedPrefsFlag: SharedPreferencesFeature): FeatureFlags
}
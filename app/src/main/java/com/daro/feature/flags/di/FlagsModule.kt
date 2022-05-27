package com.daro.feature.flags.di

import com.daro.feature.flags.data.LocalFeature
import com.daro.feature.flags.data.RemoteFeature
import com.daro.feature.flags.domain.FeatureFlags
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalFlag

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteFlag

@Module
@InstallIn(ViewModelComponent::class)
abstract class FlagsModule {

    @LocalFlag
    @Binds
    abstract fun localFlags(localFeature: LocalFeature): FeatureFlags

    @RemoteFlag
    @Binds
    abstract fun remoteFlags(remoteFeature: RemoteFeature): FeatureFlags
}
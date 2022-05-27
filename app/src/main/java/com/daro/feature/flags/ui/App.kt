package com.daro.feature.flags.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object Constants {
        const val FEATURE_1 = "feature_name1"
        const val FEATURE_2 = "feature_name2"
    }
}
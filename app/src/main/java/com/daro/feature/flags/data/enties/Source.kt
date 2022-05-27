package com.daro.feature.flags.data.enties

sealed class Source {
    object ConfigJson : Source()
    object Firebase : Source()
    object SharedPrefs : Source()
    object Retrofit : Source()

}
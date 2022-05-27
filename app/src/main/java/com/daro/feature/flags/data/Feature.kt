package com.daro.feature.flags.data

data class Feature(
    val key: String,
    val source: Source
)

sealed class Source {
    object Local : Source()
    object Remote : Source()
}
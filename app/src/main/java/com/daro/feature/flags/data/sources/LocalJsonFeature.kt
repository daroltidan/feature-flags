package com.daro.feature.flags.data.sources

import android.content.Context
import com.daro.feature.flags.domain.FeatureFlags
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject


class LocalJsonFeature @Inject constructor(
    @ApplicationContext private val context: Context
) : FeatureFlags {

    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    override suspend fun isEnabled(feature: String): Boolean {
        val json = getJsonDataFromAsset() ?: return false
        val jsonMap = getJsonMap(json)
        return jsonMap[feature] as? Boolean ?: false
    }

    private fun getJsonMap(json: String): Map<String, Any> {
        val type = Types.newParameterizedType(
            MutableMap::class.java,
            String::class.java,
            Any::class.java
        )

        val adapter = moshi.adapter<Map<String, String>>(type)
        return adapter.fromJson(json) as Map<String, Any>
    }

    private fun getJsonDataFromAsset() = try {
        context.assets.open("config.json").bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        null
    }

}
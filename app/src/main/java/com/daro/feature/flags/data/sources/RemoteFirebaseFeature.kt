package com.daro.feature.flags.data.sources

import com.daro.feature.flags.domain.FeatureFlags
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteFirebaseFeature @Inject constructor() : FeatureFlags {

    private val db = Firebase.firestore

    override suspend fun isEnabled(feature: String): Boolean {
        val flags = db.collection("flags").get().await()
        val flag = flags.documents.first()
        return flag.get(feature) as? Boolean ?: false
    }
}
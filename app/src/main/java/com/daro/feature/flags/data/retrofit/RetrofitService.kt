package com.daro.feature.flags.data.retrofit

import retrofit2.http.GET

interface RetrofitService {

    @GET("daroltidan/feature-flags/master/db.json")
    suspend fun getDb(): Map<String, Boolean>
}

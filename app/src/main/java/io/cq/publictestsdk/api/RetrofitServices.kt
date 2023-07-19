package io.cq.publictestsdk.api

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("fact")
    fun getRandomFact(): Call<Fact>
}
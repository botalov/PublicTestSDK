package io.cq.publictestsdk.api

object Common {
    private val BASE_URL = "https://catfact.ninja/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}
package com.example.millometeradmin.api

import com.example.millometeradmin.adapter.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("endpoint/getusers")
    suspend fun getUsers(): List<User>
}

fun createRetrofitService(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://asia-south1.gcp.data.mongodb-api.com/app/application-0-gacpq")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}

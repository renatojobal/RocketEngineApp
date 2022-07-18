package com.renatojobal.rocketEngine.repository

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("annotate")
    fun annotate() : Call<JsonObject>

    @GET("spot")
    fun spot() : Call<JsonObject>

    @GET("candidate")
    fun candidate() : Call<JsonObject>
    companion object {

        private const val BASE_URL = "https://api.dbpedia-spotlight.org/en/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }


}
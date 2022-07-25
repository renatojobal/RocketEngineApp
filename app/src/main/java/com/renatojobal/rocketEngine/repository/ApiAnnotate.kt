package com.renatojobal.rocketEngine.repository

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiAnnotate {

    @GET("annotate")
    fun annotate(
        @Header("accept") accept : String = "application/json",
        @Query("text") rawText: String

    ): Call<AnnotateResponse>

    @GET("spot")
    fun spot() : Call<JsonObject>

    @GET("candidate")
    fun candidate() : Call<JsonObject>
    companion object {

        private const val BASE_URL = "https://api.dbpedia-spotlight.org/en/"

        fun create() : ApiAnnotate {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiAnnotate::class.java)

        }


    }


}
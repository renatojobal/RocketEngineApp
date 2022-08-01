package com.renatojobal.rocketEngine.repository

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiFlask {

    @GET("annotate")
    fun annotate(
        @Header("accept") accept : String = "application/json",
        @Query("text") rawText: String

    ): Call<AnnotateResponse>

    @GET("rdf")
    fun rdf(
        @Query("text") text : String,
        @Query("format") format : String = "json",
        @Query("prefix") prefix : String = "fred:",
        @Query("namespace") namespace : String = "http://www.ontologydesignpatterns.org/ont/fred/domain.owl#",
    ) : Call<JsonObject>



    companion object {

        private const val BASE_URL = "http://10.0.2.2:5000/"

        fun create() : ApiFlask {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiFlask::class.java)

        }


    }


}
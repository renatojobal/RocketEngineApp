package com.renatojobal.rocketEngine.repository

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiResources {

    @GET("{resourceType}/{resourceName}")
    fun getResource(
        @Header("accept") accept : String = "application/json",
        @Path("resourceType") resourceType: String,
        @Path("resourceName") resourceName: String
    ): Call<JsonObject>


    companion object {

        private const val BASE_URL = "http://dbpedia.org/"

        fun create() : ApiResources {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiResources::class.java)

        }


    }



}
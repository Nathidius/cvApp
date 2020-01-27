package com.example.cvapp.network

import com.example.cvapp.data.model.CV
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {

    @GET("/cv")
    suspend fun getCVList(): List<CV>

    @GET("/cv/{id}")
    suspend fun getCVDetails(@Path("id") id: String): CV

    @DELETE("/cv/{id}")
    suspend fun deleteCV(@Path("id") id: String)

    @POST("/cv")
    suspend fun addCV(@Body cv: CV)
}
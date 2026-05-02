package com.example.tutorw7

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/bucin")
    suspend fun getBucinList(): List<Bucin>

    @POST("api/bucin")
    suspend fun addBucin(@Body bucin: Bucin): Response<Bucin>

    @PUT("api/bucin/{id}")
    suspend fun updateBucin(@Path("id") id: Int, @Body bucin: Bucin): Response<Bucin>

    @DELETE("api/bucin/{id}")
    suspend fun deleteBucin(@Path("id") id: Int): Response<Unit>
}
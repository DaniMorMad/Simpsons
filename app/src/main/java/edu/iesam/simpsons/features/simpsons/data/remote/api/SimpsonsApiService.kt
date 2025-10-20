package edu.iesam.simpsons.features.simpsons.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface SimpsonsApiService {

    @GET("characters")
    suspend fun findAll(): Response<SimpsonsApiModel>

    @GET("id/{simpsonsId}.json")
    fun findById(@Path("simpsonsId") id: String): SimpsonsApiModel

}
package com.meeweel.delivery.repository.network

import com.meeweel.delivery.model.entities.NetworkDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("menu?")
    suspend fun search(@Query("category") category: String, @Query("orderBy") order: String): List<NetworkDTO>

}
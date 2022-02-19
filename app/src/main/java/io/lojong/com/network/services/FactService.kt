package io.lojong.com.network.services

import io.lojong.com.model.FactResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API Service
 */
interface FactService {
    @GET("/facts/")
    suspend fun getAllFacts() : Response<FactResponse>

}
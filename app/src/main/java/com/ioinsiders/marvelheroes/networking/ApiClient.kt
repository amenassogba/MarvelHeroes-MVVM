package com.ioinsiders.marvelheroes.networking

import androidx.annotation.Nullable
import com.ioinsiders.marvelheroes.models.ApiResponse
import com.ioinsiders.marvelheroes.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        @Nullable @Query("offset") offset: Int,
        @Query("limit") limit: Int

        ): Response<ApiResponse<List<Character>>>

}

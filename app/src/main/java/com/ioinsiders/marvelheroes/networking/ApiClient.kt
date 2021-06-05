package com.ioinsiders.marvelheroes.networking

import com.ioinsiders.marvelheroes.models.Character
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("v1/public/characters")
    suspend fun getCharacters(): Response<List<Character>>

}
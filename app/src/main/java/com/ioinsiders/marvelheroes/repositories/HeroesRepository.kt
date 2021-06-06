package com.ioinsiders.marvelheroes.repositories

import com.ioinsiders.marvelheroes.BuildConfig
import com.ioinsiders.marvelheroes.helpers.HashUtils
import com.ioinsiders.marvelheroes.db.CharacterDAO
import com.ioinsiders.marvelheroes.networking.ApiClient
import java.util.*
import javax.inject.Inject


class HeroesRepository @Inject constructor(
    private val api: ApiClient,
    private val dao: CharacterDAO
    ) {

    private val timestamp = Date().time
    private val hash = HashUtils.md5(timestamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.API_KEY)

    suspend fun getCharacters() {

    }

    suspend fun searchCharacter() {

    }



}
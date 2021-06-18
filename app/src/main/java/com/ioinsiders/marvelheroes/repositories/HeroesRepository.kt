package com.ioinsiders.marvelheroes.repositories

import com.ioinsiders.marvelheroes.BuildConfig
import com.ioinsiders.marvelheroes.db.CharacterDAO
import com.ioinsiders.marvelheroes.helpers.HashUtils
import com.ioinsiders.marvelheroes.helpers.unbox
import com.ioinsiders.marvelheroes.models.Character
import com.ioinsiders.marvelheroes.models.Result
import com.ioinsiders.marvelheroes.networking.ApiClient
import com.ioinsiders.marvelheroes.viewmodels.SortType
import java.util.*
import javax.inject.Inject


class HeroesRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val characterDAO: CharacterDAO
    ) {


    private val timestamp = Date().time.toString()
    private val apiKey = BuildConfig.API_KEY
    private val hash = HashUtils.md5(timestamp + BuildConfig.PRIVATE_KEY + apiKey)

    suspend fun getCharacters(name: String? = null, sortedBy: SortType, offset: Int = 0, limit: Int = 20 ): Result<List<Character>> {

        return when(val res = apiClient
            .getCharacters(apiKey, hash, timestamp, name, offset, limit, sortedBy.key)
            .unbox()) {
            is Result.Success -> Result.Success(res.data?.data?.results ?: listOf())
            is Result.Error -> Result.Error(res.message!!)
        }
    }


}

//Persist data
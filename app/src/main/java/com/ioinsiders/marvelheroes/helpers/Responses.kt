package com.ioinsiders.marvelheroes.helpers

import com.ioinsiders.marvelheroes.models.Result
import retrofit2.Response


fun <T> Response<T>.unbox(): Result<T> {
    return try {
        val result = this.body()
        if(this.isSuccessful && result != null) {
            Result.Success(result)
        } else {
            Result.Error(this.message())
        }
    } catch (e: Exception) {
        Result.Error(e.message ?: "An error occured")
    }
}
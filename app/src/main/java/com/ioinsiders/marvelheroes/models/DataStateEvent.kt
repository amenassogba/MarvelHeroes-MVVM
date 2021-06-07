package com.ioinsiders.marvelheroes.models

sealed class DataStateEvent<out T: Any>{

        class Success<out T: Any>(val data: T): DataStateEvent<T>()
        class Failure(val reason: String): DataStateEvent<Nothing>()
        object Loading : DataStateEvent<Nothing>()
        object Initial : DataStateEvent<Nothing>()
}

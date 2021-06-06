package com.ioinsiders.marvelheroes.models

data class ApiResponseData<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    var results: T)
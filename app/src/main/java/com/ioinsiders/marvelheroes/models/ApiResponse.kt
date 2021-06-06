package com.ioinsiders.marvelheroes.models

import com.google.gson.annotations.SerializedName


class ApiResponse<T>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    var data: T) {

    val isSuccess: Boolean get() = code == 200 || code == 201
}
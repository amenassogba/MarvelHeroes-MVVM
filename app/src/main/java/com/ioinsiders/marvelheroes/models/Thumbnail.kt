package com.ioinsiders.marvelheroes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Thumbnail (
    val path: String,
    val extension: String
) {
    val url: String get() = "$path.$extension"
}
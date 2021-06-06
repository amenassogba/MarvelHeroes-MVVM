package com.ioinsiders.marvelheroes.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    @Embedded val thumbnail: Thumbnail,
    val lastViewedDate: String
)


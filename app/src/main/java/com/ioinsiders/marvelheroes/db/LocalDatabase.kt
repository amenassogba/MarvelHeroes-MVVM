package com.ioinsiders.marvelheroes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ioinsiders.marvelheroes.models.Character

@Database(entities = [Character::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract val charactersDAO: CharacterDAO
}
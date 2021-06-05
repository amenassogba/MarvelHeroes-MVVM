package com.ioinsiders.marvelheroes.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.ioinsiders.marvelheroes.db.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class PersistanceModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app,
            LocalDatabase::class.java,
            ""
        ).build()

    @Singleton
    @Provides
    fun provideCharactersDAO(db: LocalDatabase) = db.charactersDAO

}
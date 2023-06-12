package com.techfortyone.roomlibraryv1.di

import android.content.Context
import androidx.room.Room
import com.techfortyone.roomlibraryv1.data.local.UserDatabase
import com.techfortyone.roomlibraryv1.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDbName() = DB_NAME

    @Provides
    @Singleton
    fun provideUserDatabase(dbname: String, @ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            dbname
        ).build()

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase) = database.userDao()

}
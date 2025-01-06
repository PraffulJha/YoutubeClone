package com.example.youtubeclone.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.youtubeclone.apiServices.apiCalls.ApiServices
import com.example.youtubeclone.config.Constansts
import com.example.youtubeclone.db.dao.DbQueryDao
import com.example.youtubeclone.db.localdb.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun getAPiService() : ApiServices {
        // Retrofit setup in Android for local server
        val retrofit = Retrofit.Builder()
            .baseUrl(Constansts.BASE_URL) // Replace with your port
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiServices::class.java)

        return apiService

    }

    @Provides
    @Singleton
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "YtDatabase"
            ).build()
    }
    @Provides
    @Singleton
    fun provideDbQueryDao(database: AppDatabase): DbQueryDao {
        return database.dbQueryDao()
    }

}
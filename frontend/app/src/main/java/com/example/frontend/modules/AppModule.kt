package com.example.frontend.modules

import com.example.frontend.Utility.Constants.BASE_URL
import com.example.frontend.apicall.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.frontend.repository.*


@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun providesRetrofitInstance() : ApiInterface{
        val retrofitInstance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitInstance.create(ApiInterface::class.java)

    }
    @Module
    @InstallIn(SingletonComponent::class)
    object ApiRepositoryModule {

        @Provides
        fun provideApiRepository(apiInterface: ApiInterface): ApiRepository {
            return ApiRepositoryImpl(apiInterface)
        }
    }

}



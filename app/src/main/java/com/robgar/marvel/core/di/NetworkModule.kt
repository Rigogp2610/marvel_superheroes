package com.robgar.marvel.core.di

import com.robgar.marvel.core.data.network.MarvelClient
import com.robgar.marvel.core.utils.getHash
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val BASE_URL = "https://gateway.marvel.com/"

    @Provides
    @Singleton
    fun providesClient() : OkHttpClient {
        val clientInterceptor = Interceptor { chain ->
            var request: Request = chain.request()

            val timeStamp = System.currentTimeMillis().toString()
            val apiSecret = "e663d8050a7896ae7d684fe7931bb41fb7aedb62"
            val apiKey = "f4168c401eef5a83e409519a0c8649c9"
            val hash = getHash(timeStamp, apiSecret, apiKey)

            val url = request.url.newBuilder()
                .addQueryParameter("ts", timeStamp)
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("hash", hash)
                .build()

            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(clientInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMarvelClient(retrofit: Retrofit) : MarvelClient {
        return retrofit.create(MarvelClient::class.java)
    }
}
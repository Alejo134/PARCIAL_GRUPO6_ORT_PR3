package com.example.parcial_pr3_ort.api

import com.example.parcial_pr3_ort.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val authInterceptor = okhttp3.Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", "123456789")
            .build()
        chain.proceed(request)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

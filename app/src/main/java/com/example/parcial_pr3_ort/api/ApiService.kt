package com.example.parcial_pr3_ort.api

import com.example.parcial_pr3_ort.data.dao.LoginResponse
import com.example.parcial_pr3_ort.data.dao.UserAccount
import com.example.parcial_pr3_ort.data.dao.UserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @GET("transactions")
    suspend fun getUserAccount(): Response<UserAccount>

    @GET ("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<UserDetail>

    @POST("auth/login")
    suspend fun login(): Response<LoginResponse>

    @POST("auth/create")
    suspend fun createUser(): Response<UserDetail>
}

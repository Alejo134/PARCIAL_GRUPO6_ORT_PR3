package com.example.parcial_pr3_ort.api

import com.example.parcial_pr3_ort.data.dto.CreateAccountRequest
import com.example.parcial_pr3_ort.data.dto.LoginRequest
import com.example.parcial_pr3_ort.data.model.LoginResponse
import com.example.parcial_pr3_ort.data.model.UserAccount
import com.example.parcial_pr3_ort.data.model.UserDetail
import com.example.parcial_pr3_ort.data.model.profile.UserProfile

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @GET("transactions")
    suspend fun getUserAccount(): Response<UserAccount>

    @GET ("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<UserDetail>

    @GET("users/{id}")
    suspend fun getUserByIdProfile(@Path("id") userId: Int): Response<UserProfile>


    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/create")
    suspend fun createUser(@Body request: CreateAccountRequest): Response<UserDetail>
}

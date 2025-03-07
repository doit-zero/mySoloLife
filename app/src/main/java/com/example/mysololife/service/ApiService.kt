package com.example.mysamplapp.service

import com.example.mysamplapp.model.Response
import com.example.mysamplapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/signup")
    fun signup(@Body user: User): Call<Response>

    @POST("/login")
    fun login(@Body user: User): Call<Response>

    @GET("/logout")
    fun logout(): Call<Response>
}
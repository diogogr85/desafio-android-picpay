package com.picpay.desafio.android.data.network.picpay

import com.picpay.desafio.android.domain.entities.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsersLegacy(): Call<List<User>>

    @GET("users")
    suspend fun getUsers(): List<User>
}
package com.picpay.desafio.android.data.network.picpay

import com.picpay.desafio.android.data.models.User

class DummyPicPayServiceWrapper(
    private val service: PicPayService
) {

    suspend fun getUsers(): List<User> {
        val users = service.getUsers()

        return users
    }
}
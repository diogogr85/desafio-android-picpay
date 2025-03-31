package com.picpay.desafio.android

import com.picpay.desafio.android.data.network.picpay.PicPayService
import com.picpay.desafio.android.domain.entities.User

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsersLegacy().execute()

        return users.body() ?: emptyList()
    }
}
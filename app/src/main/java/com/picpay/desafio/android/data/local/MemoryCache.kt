package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.models.User

class MemoryCache {

    private var listInMemory : List<User>? = null

    fun getListInCache(): List<User>? = listInMemory

    fun updateListInCache(list: List<User>) {
        listInMemory = list
    }
}
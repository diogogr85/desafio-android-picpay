package com.picpay.desafio.android.data.network.repositoriy

import com.picpay.desafio.android.USERS
import com.picpay.desafio.android.data.network.picpay.PicPayService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PicPayRepositoryTest {

    private val apiService = mockk<PicPayService>(relaxed = true)
    private val repository = PicPayRepositoryImpl(apiService)

    @Test
    fun `should return a list of users when apiService returns success`() = runBlocking {
        coEvery { apiService.getUsers() } returns USERS

        val result = repository.getUsers().single()

        assertEquals(USERS, result)
    }
}
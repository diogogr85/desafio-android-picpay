package com.picpay.desafio.android.data.network.picpay

import com.picpay.desafio.android.USERS
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PicPayServiceTest {

    private val api = mockk<PicPayService>()
    private val service = DummyPicPayServiceWrapper(api)

    @Test
    fun `should return users from api`() = runBlocking {
        coEvery { api.getUsers() } returns USERS

        val users = service.getUsers()

        assertEquals(USERS, users)
    }
}
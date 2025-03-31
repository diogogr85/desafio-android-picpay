package com.picpay.desafio.android

import com.picpay.desafio.android.data.network.picpay.PicPayService
import com.picpay.desafio.android.domain.entities.User
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mockk<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mockk<Call<List<User>>>()
        val expectedUsers = emptyList<User>()

        every { call.execute() } returns Response.success(expectedUsers)
        every { api.getUsersLegacy() } returns call

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}
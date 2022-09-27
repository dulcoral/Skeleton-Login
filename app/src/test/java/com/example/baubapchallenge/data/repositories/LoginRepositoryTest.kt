package com.example.baubapchallenge.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.baubapchallenge.domain.Outcome
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class LoginRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        repository = LoginRepository()
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When the credentials received are valid then login success`() = runTest {
        val email = "prueba@gmail.com"
        val password = "password"

        val response = repository.login(email, password)

        Assert.assertEquals(
            response,
            Outcome.Success(Unit)
        )
    }

    @Test
    fun `When the credentials received are valid but are uppercase then login success`() = runTest {
        val email = "prueba@GMAIL.com"
        val password = "Password"

        val response = repository.login(email, password)

        Assert.assertEquals(
            response,
            Outcome.Success(Unit)
        )
    }

    @Test
    fun `When credentials received are invalid then login fail`() = runTest {
        val email = "email"
        val password = "123"

        val response = repository.login(email, password)

        Assert.assertEquals(
            response,
            Outcome.Error(type = Outcome.Error.Type.WRONG_CREDENTIALS)
        )
    }

}
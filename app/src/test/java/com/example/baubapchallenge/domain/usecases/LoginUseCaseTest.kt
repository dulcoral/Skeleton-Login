package com.example.baubapchallenge.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.baubapchallenge.data.repositories.LoginRepository
import com.example.baubapchallenge.domain.Outcome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.*

@ExperimentalCoroutinesApi
class LoginUseCaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var mockLoginRepository = mock<LoginRepository>()

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        loginUseCase = LoginUseCase(mockLoginRepository)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When the credentials are valid should return success after invoke repository`() = runTest {
        val email = "prueba@gmail.com"
        val password = "password"

        whenever(mockLoginRepository.login(email, password)).thenReturn(Outcome.Success(Unit))

        val response = loginUseCase.invoke(email, password)

        Assert.assertEquals(
            response,
            Outcome.Success(Unit)
        )
    }

    @Test
    fun `When some error happen should return error outcome from the repository`() =
        runTest {
            val email = "prueba@gmail.com"
            val password = "password"

            whenever(mockLoginRepository.login(email, password)).thenReturn(Outcome.Error())

            val response = loginUseCase.invoke(email, password)

            Assert.assertEquals(
                response,
                Outcome.Error(type = Outcome.Error.Type.GENERIC)
            )
        }

    @Test
    fun `When the credentials are invalid then return error defined from the repository`() =
        runTest {
            val email = "dulcoral20@gmail.com"
            val password = "123password"

            whenever(
                mockLoginRepository.login(
                    email,
                    password
                )
            ).thenReturn(Outcome.Error(type = Outcome.Error.Type.WRONG_CREDENTIALS))

            val response = loginUseCase.invoke(email, password)

            Assert.assertEquals(
                response,
                Outcome.Error(type = Outcome.Error.Type.WRONG_CREDENTIALS)
            )
        }
}
package com.example.baubapchallenge.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.baubapchallenge.MainCoroutineRule
import com.example.baubapchallenge.domain.Outcome
import com.example.baubapchallenge.domain.model.LoginAction
import com.example.baubapchallenge.domain.usecases.LoginUseCase
import com.example.baubapchallenge.getOrAwaitValueTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mockLoginUseCase = mock<LoginUseCase>()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = spy(LoginViewModel(mockLoginUseCase))
    }

    @Test
    fun `When the credentials received are valid then login success`() = mainCoroutineRule.run {
        val email: MutableStateFlow<String> = MutableStateFlow("prueba@gmail.com")
        val password: MutableStateFlow<String> = MutableStateFlow("password")
        whenever(viewModel.email).thenReturn(email)
        whenever(viewModel.password).thenReturn(password)
        whenever(
            mockLoginUseCase.invoke(
                "prueba@gmail.com",
                "password"
            )
        ).thenReturn(Outcome.Success(Unit))
        viewModel.onLoginButtonClicked()
        viewModel.run {
            Assert.assertEquals(action.getOrAwaitValueTest(), LoginAction.LoginSuccess)
            org.junit.Assert.assertNotEquals(
                action.getOrAwaitValueTest(),
                LoginAction.LoginFailure
            )
        }
    }

    @Test
    fun `When credentials received are invalid then login fail`() = mainCoroutineRule.run {
        val email: MutableStateFlow<String> = MutableStateFlow("test")
        val password: MutableStateFlow<String> = MutableStateFlow("prueba")
        whenever(viewModel.email).thenReturn(email)
        whenever(viewModel.password).thenReturn(password)
        whenever(
            mockLoginUseCase.invoke(
                "test",
                "prueba"
            )
        ).thenReturn(Outcome.Error(type = Outcome.Error.Type.WRONG_CREDENTIALS))
        viewModel.onLoginButtonClicked()
        viewModel.run {
            org.junit.Assert.assertNotEquals(action.getOrAwaitValueTest(), LoginAction.LoginSuccess)
            Assert.assertEquals(
                action.getOrAwaitValueTest(),
                LoginAction.LoginFailure

            )
        }
    }
}
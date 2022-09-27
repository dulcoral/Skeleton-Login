package com.example.baubapchallenge.domain.usecases

import com.example.baubapchallenge.data.repositories.ILoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: ILoginRepository) {
    fun invoke(email: String, password: String) = loginRepository.login(email, password)
}

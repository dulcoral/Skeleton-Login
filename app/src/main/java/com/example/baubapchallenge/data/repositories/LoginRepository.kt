package com.example.baubapchallenge.data.repositories

import com.example.baubapchallenge.domain.Outcome
import javax.inject.Inject

class LoginRepository @Inject constructor() : ILoginRepository {
    override fun login(email: String, password: String): Outcome<Unit> {
        return if (email.lowercase() == EMAIL && password.lowercase() == PASSWORD) {
            Outcome.Success(Unit)
        } else {
            Outcome.Error(type = Outcome.Error.Type.WRONG_CREDENTIALS)
        }
    }

    companion object {
        const val EMAIL = "prueba@gmail.com"
        const val PASSWORD = "password"
    }
}


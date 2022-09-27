package com.example.baubapchallenge.data.repositories

import com.example.baubapchallenge.domain.Outcome
import javax.inject.Inject

class LoginRepository @Inject constructor() : ILoginRepository {
    override fun login(email: String, password: String): Outcome<Unit> {
        return if (email == "prueba@gmail.com" && password == "password") {
            Outcome.Success(Unit)
        } else {
            Outcome.Error(type = Outcome.Error.Type.WRONG_CREDENTIALS)
        }
    }


}


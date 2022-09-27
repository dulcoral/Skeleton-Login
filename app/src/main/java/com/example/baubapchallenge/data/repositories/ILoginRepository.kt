package com.example.baubapchallenge.data.repositories

import com.example.baubapchallenge.domain.Outcome

interface ILoginRepository {
    fun login(email: String, password: String): Outcome<Unit>
}
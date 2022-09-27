package com.example.baubapchallenge.domain

sealed class Outcome<out T> {
    data class Success<out T>(val data: T) : Outcome<T>()
    data class Error(val message: String = "", val type: Type = Type.GENERIC) : Outcome<Nothing>() {
        enum class Type { GENERIC, WRONG_CREDENTIALS }
    }

    val value: T? get() = if (this is Success) data else null
}

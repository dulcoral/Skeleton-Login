package com.example.baubapchallenge.domain.model

sealed class LoginAction {
    object LoginError : LoginAction()
    object LoginFailure : LoginAction()
    object LoginSuccess : LoginAction()
    object LoginLoading : LoginAction()

}

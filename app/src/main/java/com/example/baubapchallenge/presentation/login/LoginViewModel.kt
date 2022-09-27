package com.example.baubapchallenge.presentation.login

import androidx.lifecycle.*
import com.example.baubapchallenge.domain.Outcome
import com.example.baubapchallenge.domain.model.LoginAction
import com.example.baubapchallenge.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), LifecycleObserver {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _action = MutableLiveData<LoginAction>()
    val action: LiveData<LoginAction> get() = _action


    fun onLoginButtonClicked() = viewModelScope.launch {
        _action.value = LoginAction.LoginLoading
        when (val result = loginUseCase.invoke(email.value, password.value)) {
            is Outcome.Success<*> -> {
                _action.value = LoginAction.LoginSuccess
            }
            is Outcome.Error -> {
                if (result.type == Outcome.Error.Type.WRONG_CREDENTIALS) {
                    _action.value = LoginAction.LoginFailure
                } else {
                    _action.value = LoginAction.LoginError
                }
            }
        }
    }


}
package com.example.baubapchallenge.presentation.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baubapchallenge.R
import com.example.baubapchallenge.databinding.FragmentLoginBinding
import com.example.baubapchallenge.domain.model.LoginAction
import com.example.baubapchallenge.presentation.base.BaseFragment
import com.example.baubapchallenge.utils.isValidEmail
import com.example.baubapchallenge.utils.observe
import com.google.android.material.internal.TextWatcherAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLogin : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        observe(viewModel.action, ::handleActions)
        initListeners()
    }

    private fun handleActions(action: LoginAction) {
        when (action) {
            is LoginAction.LoginLoading -> showProgressDialog()
            is LoginAction.LoginError -> {
                dismissProgressDialog()
                showMessage(getString(R.string.login_error))
            }
            is LoginAction.LoginFailure -> {
                dismissProgressDialog()
                showMessage(getString(R.string.login_failure))
            }
            is LoginAction.LoginSuccess -> {
                dismissProgressDialog()
                showMessage(getString(R.string.login_success))
            }
        }
    }

    private fun initListeners() {
        binding.editTextEmail.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                binding.apply {
                    if (text.toString().isValidEmail()) {
                        textInputLayoutEmail.isErrorEnabled = false
                        buttonLogin.isEnabled = true
                    } else {
                        textInputLayoutEmail.isErrorEnabled = true
                        buttonLogin.isEnabled = false
                        textInputLayoutEmail.error = getString(R.string.email_error)
                    }
                }
            }
        })

        binding.editTextPassword.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(editable: Editable) {
                binding.apply {
                    if (editable.toString().isBlank()) {
                        textInputLayoutPassword.error = getString(R.string.password_error)
                        textInputLayoutEmail.isErrorEnabled = true
                        buttonLogin.isEnabled = false

                    } else {
                        textInputLayoutPassword.isErrorEnabled = false
                        buttonLogin.isEnabled = true

                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
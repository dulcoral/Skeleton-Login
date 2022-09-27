package com.example.baubapchallenge.utils

import androidx.core.util.PatternsCompat

fun String?.isValidEmail(): Boolean =
    this.let { PatternsCompat.EMAIL_ADDRESS.matcher(it).matches() }


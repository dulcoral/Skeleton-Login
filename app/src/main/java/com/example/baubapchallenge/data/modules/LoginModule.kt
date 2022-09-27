package com.example.baubapchallenge.data.modules

import com.example.baubapchallenge.data.repositories.ILoginRepository
import com.example.baubapchallenge.data.repositories.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {
    @Binds
    abstract fun provideLocalLoginRepository(repository: LoginRepository): ILoginRepository
}

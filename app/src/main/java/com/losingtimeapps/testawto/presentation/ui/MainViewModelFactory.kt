package com.losingtimeapps.testawto.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.losingtimeapps.testawto.domain.usecase.GetVehicleUseCase

class MainViewModelFactory(private val vehicleUseCase: GetVehicleUseCase) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            vehicleUseCase
        ) as T
    }
}
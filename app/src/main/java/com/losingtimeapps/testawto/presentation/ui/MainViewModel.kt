package com.losingtimeapps.testawto.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.losingtimeapps.testawto.domain.entity.Vehicle
import com.losingtimeapps.testawto.domain.usecase.GetVehicleUseCase
import com.losingtimeapps.testawto.presentation.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import com.losingtimeapps.testawto.domain.utils.Error

class MainViewModel(
    private val vehicleUseCase: GetVehicleUseCase
) : ViewModel() {

    var firstTime: Boolean = true

    val onErrorObservable = SingleLiveEvent<Int>()

    val onGetVehicleObservable = MutableLiveData<List<Vehicle>>()

    private val compositeDisposable = CompositeDisposable()

    fun getVehicle(latitude: Double, longitude: Double) {

        compositeDisposable.add(
            vehicleUseCase.invoke(latitude, longitude).subscribe({
                if (it.dataIsEmpty())
                    onErrorObservable.value = it.error?.value
                else {
                    onGetVehicleObservable.value = it.data
                }
            }, {
                onErrorObservable.value = Error.UnexpectedError.value
            })
        )

    }

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}

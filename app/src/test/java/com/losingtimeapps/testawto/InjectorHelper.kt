package com.losingtimeapps.testawto

import com.losingtimeapps.testawto.data.remote.VehicleRepositoryImp
import com.losingtimeapps.testawto.data.remote.VehicleService
import com.losingtimeapps.testawto.domain.boundary.BaseScheduler
import com.losingtimeapps.testawto.domain.boundary.VehicleRepository
import com.losingtimeapps.testawto.domain.usecase.GetVehicleUseCase
import com.losingtimeapps.testawto.presentation.ui.MainViewModel

class InjectorHelper {

    companion object {

        lateinit var vehicleService: VehicleService

        private fun provideBaseSchedule(): BaseScheduler = TestScheduleImp()
        private fun provideVehicleRepository(vehicleService: VehicleService): VehicleRepository =
            VehicleRepositoryImp(vehicleService)


        private fun provideVehicleUseCase(
            vehicleRepository: VehicleRepository,
            baseScheduler: BaseScheduler
        ) = GetVehicleUseCase(vehicleRepository, baseScheduler)


        private fun provideMainViewModel(vehicleUseCase: GetVehicleUseCase) =
            MainViewModel(vehicleUseCase)

        fun injectVehicleUseCase(): GetVehicleUseCase =
            provideVehicleUseCase(
                provideVehicleRepository(vehicleService),
                provideBaseSchedule()
            )

        fun injectMainViewModel(): MainViewModel = provideMainViewModel(injectVehicleUseCase())
    }


}
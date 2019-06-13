package com.losingtimeapps.testawto.di.modules

import com.losingtimeapps.testawto.domain.boundary.BaseScheduler
import com.losingtimeapps.testawto.domain.boundary.VehicleRepository
import com.losingtimeapps.testawto.domain.usecase.GetVehicleUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {


    @Provides
    fun provideGetVehicleListUseCase(
        vehicleRepository: VehicleRepository
        , baseScheduler: BaseScheduler
    ): GetVehicleUseCase {
        return GetVehicleUseCase(vehicleRepository, baseScheduler)
    }


}
package com.losingtimeapps.testawto.di.modules

import com.losingtimeapps.testawto.data.remote.VehicleRepositoryImp
import com.losingtimeapps.testawto.data.remote.VehicleService
import com.losingtimeapps.testawto.domain.boundary.BaseScheduler
import com.losingtimeapps.testawto.domain.boundary.VehicleRepository
import com.losingtimeapps.testawto.domain.usecase.GetVehicleUseCase
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule() {

    @Provides
    fun provideVehicleRepository(vehicleService: VehicleService): VehicleRepository =
        VehicleRepositoryImp(vehicleService)


}
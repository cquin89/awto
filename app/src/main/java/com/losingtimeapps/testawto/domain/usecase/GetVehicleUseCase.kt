package com.losingtimeapps.testawto.domain.usecase

import android.location.Location
import com.losingtimeapps.testawto.domain.boundary.BaseScheduler
import com.losingtimeapps.testawto.domain.boundary.VehicleRepository
import com.losingtimeapps.testawto.domain.entity.ResponseModel
import com.losingtimeapps.testawto.domain.entity.Vehicle
import io.reactivex.Observable

class GetVehicleUseCase(
    val vehicleRepository: VehicleRepository,
    val baseScheduler: BaseScheduler
) : UseCase(baseScheduler) {

    fun invoke(latitude: Double, longitude: Double): Observable<ResponseModel<List<Vehicle>>> {

        return vehicleRepository.getVehicleList()
            .map {
                if (!it.dataIsEmpty()) {
                    it.data?.forEach {
                        it.distanceFromUser =
                            calculateDistance(latitude, longitude, it.latitude ?: 0.0, it.longitude ?: 0.0)
                    }
                    ResponseModel(it.data?.sortedBy { vehicle -> vehicle.distanceFromUser }!!)

                } else
                    it
            }
            .subscribeOn(baseScheduler.io())
            .observeOn(baseScheduler.ui())

    }

    fun calculateDistance(
        latitude: Double,
        longitude: Double,
        latitude2: Double,
        longitude2: Double
    ): Float {
        val location = Location("localizacion 1")
        location.latitude = latitude  //latitud
        location.longitude = longitude //longitud
        val location2 = Location("localizacion 2")
        location2.latitude = latitude2  //latitud
        location2.longitude = longitude2 //longitud
        return (location.distanceTo(location2) / 1000)
    }
}
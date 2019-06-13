package com.losingtimeapps.testawto.domain.boundary

import com.losingtimeapps.testawto.domain.entity.ResponseModel
import com.losingtimeapps.testawto.domain.entity.Vehicle
import io.reactivex.Observable


interface VehicleRepository {

    fun getVehicleList(): Observable<ResponseModel<List<Vehicle>>>

}
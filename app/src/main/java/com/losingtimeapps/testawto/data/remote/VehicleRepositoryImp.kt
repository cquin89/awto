package com.losingtimeapps.testawto.data.remote

import com.losingtimeapps.testawto.domain.boundary.VehicleRepository
import com.losingtimeapps.testawto.domain.entity.ResponseModel
import com.losingtimeapps.testawto.domain.entity.Vehicle
import com.losingtimeapps.testawto.domain.utils.ParseError
import io.reactivex.Observable


open class VehicleRepositoryImp(
    private val vehicleService: VehicleService
) : VehicleRepository {


    override fun getVehicleList(): Observable<ResponseModel<List<Vehicle>>> {
        return vehicleService.getGitHubRepository("c3DmqaycE0")
            .map { ResponseModel(it) }
            .onErrorReturn {
                ResponseModel(ParseError().parse(it))
            }
    }
}
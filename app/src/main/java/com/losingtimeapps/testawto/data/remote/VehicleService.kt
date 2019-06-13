package com.losingtimeapps.testawto.data.remote

import com.losingtimeapps.testawto.domain.entity.Vehicle
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface VehicleService {

    @GET("vehicle-list")
    fun getGitHubRepository(@Query("apiKey") apiKey:String): Observable<List<Vehicle>>


}
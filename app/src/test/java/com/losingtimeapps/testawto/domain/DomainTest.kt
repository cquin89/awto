package com.losingtimeapps.testawto.domain

import com.losingtimeapps.testawto.InjectorHelper
import com.losingtimeapps.testawto.TestScheduleImp
import com.losingtimeapps.testawto.data.remote.VehicleService
import com.losingtimeapps.testawto.domain.boundary.VehicleRepository
import com.losingtimeapps.testawto.domain.entity.ResponseModel
import com.losingtimeapps.testawto.domain.entity.Vehicle
import com.losingtimeapps.testawto.domain.usecase.GetVehicleUseCase
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import  com.losingtimeapps.testawto.domain.utils.Error

class DomainTest {

    private lateinit var getVehicleUseCase: GetVehicleUseCase

    @Mock
    lateinit var vehicleRepository: VehicleRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        getVehicleUseCase = GetVehicleUseCase(vehicleRepository, TestScheduleImp())
    }


    @Test
    fun when_GetVehicleFailed_Expect_GetVehicleError() {

        Mockito.`when`(
            vehicleRepository.getVehicleList()
        ).thenReturn(Observable.just(ResponseModel(Error.GetVehicleError)))

        val testSubscriber = getVehicleUseCase.invoke(0.0, 0.0).test()
        testSubscriber.assertValue(ResponseModel(Error.GetVehicleError))
        testSubscriber.dispose()
    }

    @Test
    fun when_GetVehicleSuccess_Expect_ListVehicle() {

        val response = ResponseModel<List<Vehicle>>(listOf())
        Mockito.`when`(
            vehicleRepository.getVehicleList()
        ).thenReturn(Observable.just(response))

        val testSubscriber = getVehicleUseCase.invoke(0.0, 0.0).test()
        testSubscriber.assertValue(response)
        testSubscriber.dispose()
    }

}
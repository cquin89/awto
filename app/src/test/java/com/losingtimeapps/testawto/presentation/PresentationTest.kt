package com.losingtimeapps.testawto.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.jraska.livedata.TestObserver
import com.losingtimeapps.testawto.InjectorHelper
import com.losingtimeapps.testawto.RxImmediateSchedulerRule
import com.losingtimeapps.testawto.data.remote.VehicleService
import com.losingtimeapps.testawto.domain.entity.ResponseModel
import com.losingtimeapps.testawto.domain.entity.Vehicle
import com.losingtimeapps.testawto.presentation.ui.MainViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PresentationTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var vehicleService: VehicleService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        InjectorHelper.vehicleService = vehicleService
        mainViewModel = InjectorHelper.injectMainViewModel()
    }


    @Test
    fun when_getVehicleListFailed_Expect_Error() {

        Mockito.`when`(
            vehicleService.getGitHubRepository(Mockito.anyString())
        ).thenReturn(Observable.error(Throwable()))

        mainViewModel.getVehicle(0.0, 0.0)
        val testSubscriber = mainViewModel.onGetVehicleObservable.test()
        val testErrorSubscriber = mainViewModel.onErrorObservable.test()

        testErrorSubscriber.awaitValue()
        testErrorSubscriber.assertHasValue()
        testSubscriber.assertNoValue()
    }

    @Test
    fun when_getVehicleListSuccess_VehicleList() {
        val response: List<Vehicle> = listOf()
        Mockito.`when`(
            vehicleService.getGitHubRepository(Mockito.anyString())
        ).thenReturn(Observable.just(response))

        mainViewModel.getVehicle(0.0, 0.0)
        val testSubscriber = mainViewModel.onGetVehicleObservable.test()
        val testErrorSubscriber = mainViewModel.onErrorObservable.test()

        testSubscriber.awaitValue()
        testSubscriber.assertHasValue()
        testErrorSubscriber.assertNoValue()
    }


    private fun <T> LiveData<T>.test(): TestObserver<T> {
        return TestObserver.test(this)
    }

}
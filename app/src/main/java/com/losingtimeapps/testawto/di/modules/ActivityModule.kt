package com.losingtimeapps.testawto.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.losingtimeapps.testawto.di.qualifiers.ForActivity
import com.losingtimeapps.testawto.domain.usecase.GetVehicleUseCase
import com.losingtimeapps.testawto.presentation.ui.MainActivity
import com.losingtimeapps.testawto.presentation.ui.MainViewModel
import com.losingtimeapps.testawto.presentation.ui.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ForActivity
    @Provides
    internal fun provideContext(): Context {
        return activity
    }

    @Provides
    internal fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun provideMainViewModelFactory(getVehicleUseCase: GetVehicleUseCase) = MainViewModelFactory(getVehicleUseCase)

    @Provides
    fun provideMainViewModel(@ForActivity activity: Context, mainViewModelFactory: MainViewModelFactory)
            : MainViewModel = ViewModelProviders.of(activity as MainActivity, mainViewModelFactory)
        .get(MainViewModel::class.java)

}


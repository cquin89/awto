package com.losingtimeapps.testawto.app

import com.losingtimeapps.testawto.di.modules.NetworkModule
import com.losingtimeapps.testawto.presentation.ui.MainActivityComponent
import com.losingtimeapps.testawto.di.modules.ActivityModule
import com.losingtimeapps.testawto.di.modules.RepositoryModule
import com.losingtimeapps.testawto.di.modules.UseCaseModule
import com.losingtimeapps.testawto.domain.usecase.UseCase
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, RepositoryModule::class, UseCaseModule::class])
interface ApplicationComponent {

    fun inject(app: App)

    fun createMainActivityComponent(module: ActivityModule): MainActivityComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }
}

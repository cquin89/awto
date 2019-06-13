package com.losingtimeapps.testawto.presentation.ui

import com.losingtimeapps.testawto.di.modules.ActivityModule
import com.losingtimeapps.testawto.di.scopes.ActivityScope

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}

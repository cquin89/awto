package com.losingtimeapps.testawto.app

import android.content.Context
import com.losingtimeapps.testawto.di.qualifiers.ForApplication
import com.losingtimeapps.testawto.domain.boundary.BaseScheduler
import com.losingtimeapps.testawto.presentation.schedule.ScheduleImp
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @ForApplication
    @Provides
    internal fun provideApplicationContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    fun provideScheduler(): BaseScheduler = ScheduleImp()

}

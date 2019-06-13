package com.losingtimeapps.testawto.domain.boundary

import io.reactivex.Scheduler

interface BaseScheduler {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
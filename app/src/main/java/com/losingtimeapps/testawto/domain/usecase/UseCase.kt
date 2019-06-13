package com.losingtimeapps.testawto.domain.usecase

import com.losingtimeapps.testawto.domain.boundary.BaseScheduler

open class UseCase(private val baseScheduler: BaseScheduler) {

    fun uiInvoke() = baseScheduler.ui()

    fun computationInvoke() = baseScheduler.computation()

    fun ioInvoke() = baseScheduler.io()
}
package com.losingtimeapps.testawto.domain.utils

import com.losingtimeapps.testawto.R
import retrofit2.HttpException
import java.net.UnknownHostException

class ParseError {

    fun parse(throwable: Throwable): Error {

        return when (throwable) {
            is HttpException -> when (throwable.code()) {
                404 -> Error.NotFoundError
                500 -> Error.InternalServerError
                else -> Error.UnexpectedError
            }
            is UnknownHostException -> Error.NetworkConnectionError
            else -> Error.UnexpectedError
        }
    }
}

enum class Error(val value: Int) {


    // vehicle error
    GetVehicleError(R.string.app_name),

    // http error
    NotFoundError(R.string.app_name),
    NetworkConnectionError(R.string.app_name),
    UnexpectedError(R.string.app_name),
    InternalServerError(R.string.app_name)

}
package com.makuta.fiatconverter.utils

import retrofit2.HttpException

sealed class NetResult<out T> {
    data class Success<T>(val data: T) : NetResult<T>()
    data class Error(val code: Int?, val message: String?) : NetResult<Nothing>()
}

suspend fun <T> safeNetCall(apiCall: suspend () -> T): NetResult<T> =
    try {
        NetResult.Success(apiCall())
    } catch (e: HttpException) {
        NetResult.Error(e.code(), e.message())
    } catch (e: Throwable) {
        NetResult.Error(null, e.message)
    }
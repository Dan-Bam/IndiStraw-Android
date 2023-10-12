package com.danbam.indistraw.core.data.remote.util

import com.danbam.indistraw.core.exception.ConflictDataException
import com.danbam.indistraw.core.exception.ExpiredTokenException
import com.danbam.indistraw.core.exception.InvalidTokenException
import com.danbam.indistraw.core.exception.NotFoundException
import com.danbam.indistraw.core.exception.ServerErrorException
import com.danbam.indistraw.core.exception.TooManyRequestException
import com.danbam.indistraw.core.exception.UnKnownHttpException
import com.danbam.indistraw.core.exception.WrongDataException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

data class Error(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
)

suspend inline fun <T> indiStrawApiCall(
    crossinline callFunction: suspend () -> T,
): T =
    try {
        withContext(Dispatchers.IO) {
            callFunction()
        }
    } catch (e: HttpException) {
        val error = e.getError() ?: Error("", e.code())
        throw when (error.status) {
            400 -> WrongDataException(error.message)
            401 -> InvalidTokenException(error.message)
            404 -> NotFoundException(error.message)
            409 -> ConflictDataException(error.message)
            429 -> TooManyRequestException(error.message)
            in 500..600 -> ServerErrorException(error.message)
            else -> UnKnownHttpException(error.message)
        }
    } catch (e: ExpiredTokenException) {
        throw ExpiredTokenException()
    }

fun <T> Response<T>.errorHandling() {
    if (!isSuccessful) {
        throw when (code()) {
            400 -> WrongDataException(message())
            401 -> InvalidTokenException(message())
            404 -> NotFoundException(message())
            409 -> ConflictDataException(message())
            429 -> TooManyRequestException(message())
            in 500..600 -> ServerErrorException(message())
            else -> UnKnownHttpException(message())
        }
    }
}

fun HttpException.getError(): Error? =
    response()?.errorBody()?.let { Gson().fromJson(it.string(), Error::class.java) }
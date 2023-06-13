package com.danbam.data.remote.util

import com.danbam.domain.exception.ConflictDataException
import com.danbam.domain.exception.ExpiredTokenException
import com.danbam.domain.exception.InvalidTokenException
import com.danbam.domain.exception.NoContentException
import com.danbam.domain.exception.NotFoundException
import com.danbam.domain.exception.ServerErrorException
import com.danbam.domain.exception.TooManyRequestException
import com.danbam.domain.exception.UnKnownHttpException
import com.danbam.domain.exception.WrongDataException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.NullPointerException

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
        val error = getError(exception = e) ?: Error("", e.code())
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
    } catch (e: KotlinNullPointerException) {
        throw NoContentException(e.message)
    }

fun getError(exception: HttpException): Error? =
    exception.response()?.errorBody()?.let { Gson().fromJson(it.string(), Error::class.java) }
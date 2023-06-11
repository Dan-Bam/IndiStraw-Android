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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.NullPointerException

suspend inline fun <T> indiStrawApiCall(
    crossinline callFunction: suspend () -> T,
): T =
    try {
        withContext(Dispatchers.IO) {
            callFunction()
        }
    } catch (e: HttpException) {
        throw when (e.code()) {
            400 -> WrongDataException(e.message)
            401 -> InvalidTokenException(e.message)
            404 -> NotFoundException(e.message)
            409 -> ConflictDataException(e.message)
            429 -> TooManyRequestException(e.message)
            in 500..600 -> ServerErrorException(e.message)
            else -> UnKnownHttpException(e.message)
        }
    } catch (e: ExpiredTokenException) {
        throw ExpiredTokenException()
    } catch (e: KotlinNullPointerException) {
        throw NoContentException(e.message)
    }
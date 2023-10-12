package com.danbam.indistraw.tv.util.android

import android.util.Log
import com.danbam.indistraw.core.exception.ConflictDataException
import com.danbam.indistraw.core.exception.ExpiredTokenException
import com.danbam.indistraw.core.exception.InvalidTokenException
import com.danbam.indistraw.core.exception.NotFoundException
import com.danbam.indistraw.core.exception.ServerErrorException
import com.danbam.indistraw.core.exception.TooManyRequestException
import com.danbam.indistraw.core.exception.WrongDataException

suspend fun Throwable.errorHandling(
    unknownAction: suspend () -> Unit,
    wrongDataException: suspend () -> Unit = unknownAction,
    invalidTokenException: suspend () -> Unit = unknownAction,
    notFoundException: suspend () -> Unit = unknownAction,
    conflictException: suspend () -> Unit = unknownAction,
    serverErrorException: suspend () -> Unit = unknownAction,
    expiredTokenException: suspend () -> Unit = unknownAction,
    tooManyRequestException: suspend () -> Unit = unknownAction,
) =
    when (this) {
        is WrongDataException -> {
            errorLog("WrongDataException", message)
            wrongDataException()
        }

        is InvalidTokenException -> {
            errorLog("InvalidTokenException", message)
            invalidTokenException()
        }

        is NotFoundException -> {
            errorLog("NotFoundException", message)
            notFoundException()
        }

        is ConflictDataException -> {
            errorLog("ConflictDataException", message)
            conflictException()
        }

        is ServerErrorException -> {
            errorLog("ServerErrorException", message)
            serverErrorException()
        }

        is ExpiredTokenException -> {
            errorLog("ExpiredTokenException", message)
            expiredTokenException()
        }

        is TooManyRequestException -> {
            errorLog("TooManyRequestException", message)
            tooManyRequestException()
        }

        else -> {
            errorLog("UnKnownException", message)
            unknownAction()
        }
    }

private fun errorLog(tag: String, msg: String?) {
    Log.e(tag, msg ?: "")
}
package com.danbam.presentation.util.android

import android.util.Log
import com.danbam.domain.exception.ConflictDataException
import com.danbam.domain.exception.ExpiredTokenException
import com.danbam.domain.exception.InvalidTokenException
import com.danbam.domain.exception.NoContentException
import com.danbam.domain.exception.NotFoundException
import com.danbam.domain.exception.ServerErrorException
import com.danbam.domain.exception.WrongDataException

suspend fun Throwable.errorHandling(
    unknownAction: suspend () -> Unit,
    wrongDataException: suspend () -> Unit = unknownAction,
    invalidTokenException: suspend () -> Unit = unknownAction,
    notFoundException: suspend () -> Unit = unknownAction,
    conflictException: suspend () -> Unit = unknownAction,
    serverErrorException: suspend () -> Unit = unknownAction,
    expiredTokenException: suspend () -> Unit = unknownAction,
    noContentException: suspend () -> Unit = unknownAction,
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

        is NoContentException -> {
            errorLog("NoContentException", message)
            noContentException()
        }

        else -> {
            errorLog("UnKnownException", message)
            unknownAction()
        }
    }

private fun errorLog(tag: String, msg: String?) {
    Log.e(tag, msg ?: "")
}
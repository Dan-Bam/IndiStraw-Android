package com.danbam.domain.exception

class WrongDataException(
    override val message: String?,
) : RuntimeException()

class InvalidTokenException(
    override val message: String?,
) : RuntimeException()

class NotFoundException(
    override val message: String?,
) : RuntimeException()

class ConflictDataException(
    override val message: String?,
) : RuntimeException()

class ServerErrorException(
    override val message: String?,
) : RuntimeException()

class UnKnownHttpException(
    override val message: String?,
) : RuntimeException()

class TooManyRequestException(
    override val message: String?,
) : RuntimeException()

class NoContentException(
    override val message: String?,
) : RuntimeException()
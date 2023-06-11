package com.danbam.domain.usecase.file

import com.danbam.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class SendFileUseCase @Inject constructor(
    private val fileRepository: FileRepository,
) {
    suspend operator fun invoke(file: File) = runCatching {
        fileRepository.sendFile(file = file)
    }
}
package com.danbam.indistraw.domain.usecase.address

import com.danbam.indistraw.domain.repository.AddressRepository
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(
    private val addressRepository: AddressRepository,
) {
    suspend operator fun invoke(keyword: String) = runCatching {
        addressRepository.getAddress(keyword = keyword)
    }
}
package com.danbam.domain.usecase.address

import com.danbam.domain.repository.AddressRepository
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(
    private val addressRepository: AddressRepository,
) {
    suspend operator fun invoke(keyword: String) = runCatching {
        addressRepository.getAddress(keyword = keyword)
    }
}
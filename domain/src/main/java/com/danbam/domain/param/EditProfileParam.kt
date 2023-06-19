package com.danbam.domain.param

data class EditProfileParam(
    val name: String,
    val phoneNumber: String,
    val address: AddressParam?,
    val profileUrl: String?,
) {
    data class AddressParam(
        val zipcode: String,
        val streetAddress: String,
        val detailAddress: String?,
    )
}
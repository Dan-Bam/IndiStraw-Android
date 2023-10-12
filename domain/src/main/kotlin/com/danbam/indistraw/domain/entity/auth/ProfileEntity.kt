package com.danbam.indistraw.domain.entity.auth

data class ProfileEntity(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val zipcode: String?,
    val address: String?,
    val profileUrl: String?,
)
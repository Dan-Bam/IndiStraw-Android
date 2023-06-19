package com.danbam.domain.entity

data class ProfileEntity(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val address: String?,
    val profileUrl: String?,
)
package com.finance.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequest(
    val name: String,
    val balance: Double
)
package com.finance.model

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: Int,
    val name: String,
    val balance: Double
)
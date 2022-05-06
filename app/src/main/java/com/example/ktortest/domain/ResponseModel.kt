package com.example.ktortest.domain

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val title: String,
    val description: String,
    val image: String,
    val price: Double
)
package com.example.ktortest.domain

import kotlinx.serialization.Serializable

@Serializable
data class RequestModel(
    val title: String,
    val description: String,
    val image: String
)
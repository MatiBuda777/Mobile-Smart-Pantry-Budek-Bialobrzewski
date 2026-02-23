package com.example.mobile_smart_pantry_project_iv.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val uuid: String,
    val name: String,
    val quantity: Int,
    val category: String,
    val imageRef: String
) : java.io.Serializable

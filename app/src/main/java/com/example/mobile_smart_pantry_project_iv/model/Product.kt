package com.example.mobile_smart_pantry_project_iv.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class Product( // Implement the Parcelable
    val uuid: String,
    val name: String,
    var quantity: Int,
    val category: String,
    val imageRef: String
) : java.io.Serializable, Parcelable {
}

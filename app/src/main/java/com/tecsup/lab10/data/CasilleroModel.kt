package com.tecsup.lab10.data

import com.google.gson.annotations.SerializedName

data class CasilleroModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("location")
    var location: String,
    @SerializedName("price")
    var price: Double,
    @SerializedName("description")
    var description: String,
    @SerializedName("blocked")
    var blocked: Boolean,
    @SerializedName("open")
    var open: Boolean,
    @SerializedName("occupied")
    var occupied: Boolean,
    @SerializedName("userId")
    var userId: String
)

package com.erros.minimax.ciceronetoothpick.model.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by milkman on 31.01.18.
 */
data class Image(
        @SerializedName("filename") val filename: String,
        @SerializedName("id") val id: Int,
        @SerializedName("width") val width: Int,
        @SerializedName("height") val height: Int
)
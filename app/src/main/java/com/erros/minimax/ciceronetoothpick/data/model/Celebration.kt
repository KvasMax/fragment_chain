package com.erros.minimax.ciceronetoothpick.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by milkman on 06.02.18.
 */
data class Celebration(
        @SerializedName("title") val title: String,
        @SerializedName("colour") val colour: String,
        @SerializedName("rank") val rank: String,
        @SerializedName("rank_num") val rankNum: Double
)
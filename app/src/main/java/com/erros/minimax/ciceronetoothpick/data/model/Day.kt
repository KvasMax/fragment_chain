package com.erros.minimax.ciceronetoothpick.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by milkman on 06.02.18.
 */
data class Day(
        @SerializedName("date") val date: String,
        @SerializedName("season") val season: String,
        @SerializedName("season_week") val seasonWeek: Int,
        @SerializedName("celebrations") val celebrations: List<Celebration>,
        @SerializedName("weekday") val weekday: String
)


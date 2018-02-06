package com.erros.minimax.ciceronetoothpick.data.network

import com.erros.minimax.ciceronetoothpick.data.model.Day
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by milkman on 06.02.18.
 */
interface LiturgicalCalendarAPI {

    @GET("/api/v0/en/calendars/default/{year}/{month}")
    fun getMonthlyCalendar(@Path("year") year: Int, @Path("month") month: Int): Single<List<Day>>

}
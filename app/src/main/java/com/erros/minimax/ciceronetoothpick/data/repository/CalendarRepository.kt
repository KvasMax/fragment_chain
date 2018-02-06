package com.erros.minimax.ciceronetoothpick.data.repository

import com.erros.minimax.ciceronetoothpick.data.model.Day
import com.erros.minimax.ciceronetoothpick.data.network.LiturgicalCalendarAPI
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by milkman on 06.02.18.
 */
class CalendarRepository
@Inject constructor(private val calendarApi: LiturgicalCalendarAPI) {

    fun getMonthlyCalendar(year: Int, month: Int): Single<List<Day>> = calendarApi.getMonthlyCalendar(year, month)

}
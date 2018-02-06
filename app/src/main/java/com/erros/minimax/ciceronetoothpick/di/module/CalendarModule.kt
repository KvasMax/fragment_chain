package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.data.network.LiturgicalCalendarAPI
import com.erros.minimax.ciceronetoothpick.data.repository.CalendarRepository
import com.erros.minimax.ciceronetoothpick.di.providers.CalendarApiProvider
import com.erros.minimax.ciceronetoothpick.di.providers.CalendarServerPathProvider
import com.erros.minimax.ciceronetoothpick.di.qualifier.CalendarServerPath
import com.erros.minimax.ciceronetoothpick.presentation.calendar.list.DateListContract
import com.erros.minimax.ciceronetoothpick.presentation.calendar.list.DateListPresenter

/**
 * Created by milkman on 06.02.18.
 */
class CalendarModule : NavigationModule() {

    init {
        bind(String::class.java).withName(CalendarServerPath::class.java).toProvider(CalendarServerPathProvider::class.java).providesSingletonInScope()
        bind(LiturgicalCalendarAPI::class.java).toProvider(CalendarApiProvider::class.java).providesSingletonInScope()
        bind(CalendarRepository::class.java).singletonInScope()
        bind(DateListContract.Presenter::class.java).to(DateListPresenter::class.java).singletonInScope()
    }

}
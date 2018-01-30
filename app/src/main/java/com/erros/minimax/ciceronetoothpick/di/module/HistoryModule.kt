package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.presentation.history.detail.DetailHistoryContract
import com.erros.minimax.ciceronetoothpick.presentation.history.detail.DetailHistoryPresenter
import com.erros.minimax.ciceronetoothpick.presentation.history.group.GroupedHistoryContract
import com.erros.minimax.ciceronetoothpick.presentation.history.group.GroupedHistoryPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * Created by minimax on 1/28/18.
 */
class HistoryModule : Module() {

    init {
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        bind(GroupedHistoryContract.Presenter::class.java).to(GroupedHistoryPresenter::class.java).singletonInScope()
        bind(DetailHistoryContract.Presenter::class.java).to(DetailHistoryPresenter::class.java).instancesInScope()
    }

}
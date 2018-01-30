package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.presentation.history.detail.DetailHistoryContract
import com.erros.minimax.ciceronetoothpick.presentation.history.detail.DetailHistoryPresenter
import com.erros.minimax.ciceronetoothpick.presentation.history.group.GroupedHistoryContract
import com.erros.minimax.ciceronetoothpick.presentation.history.group.GroupedHistoryPresenter

/**
 * Created by minimax on 1/28/18.
 */
class HistoryModule : NavigationModule() {

    init {
        bind(GroupedHistoryContract.Presenter::class.java).to(GroupedHistoryPresenter::class.java).singletonInScope()
        bind(DetailHistoryContract.Presenter::class.java).to(DetailHistoryPresenter::class.java).instancesInScope()
    }

}
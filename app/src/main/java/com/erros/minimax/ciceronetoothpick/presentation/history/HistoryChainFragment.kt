package com.erros.minimax.ciceronetoothpick.presentation.history

import android.support.v4.app.Fragment
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.HistoryModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.ChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.history.detail.DetailHistoryFragment
import com.erros.minimax.ciceronetoothpick.presentation.history.group.GroupedHistoryFragment
import toothpick.Toothpick

/**
 * Created by milkman on 30.01.18.
 */
class HistoryChainFragment : ChainFragment() {

    override fun createChildFragment(screenKey: String, data: Any?): Fragment? = when (screenKey) {
        Screens.GROUPED_HISTORY -> GroupedHistoryFragment()
        Screens.DETAIL_HISTORY -> DetailHistoryFragment()
        else -> null
    }

    override val defaultScreen: String
        get() = Screens.GROUPED_HISTORY

    override fun inject() {
        Toothpick.openScopes(Scopes.MAIN_SCREEN, Scopes.HISTORY).apply {
            installModules(HistoryModule())
            Toothpick.inject(this@HistoryChainFragment, this)
        }
    }
}
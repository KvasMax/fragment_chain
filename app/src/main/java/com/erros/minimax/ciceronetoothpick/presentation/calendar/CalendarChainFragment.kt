package com.erros.minimax.ciceronetoothpick.presentation.calendar

import android.support.v4.app.Fragment
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.CalendarModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.ChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.calendar.list.DateListFragment
import toothpick.Toothpick

/**
 * Created by milkman on 06.02.18.
 */
class CalendarChainFragment : ChainFragment() {

    override fun inject() {
        Toothpick.openScopes(Scopes.MAIN_SCREEN, Scopes.CALENDAR).apply {
            installModules(CalendarModule())
            Toothpick.inject(this@CalendarChainFragment, this)
        }
    }

    override fun createChildFragment(screenKey: String, data: Any?): Fragment? = when (screenKey) {
        Screens.CALENDAR -> DateListFragment()
        else -> null
    }


    override val defaultScreen: String
        get() = Screens.CALENDAR
}
package com.erros.minimax.ciceronetoothpick.presentation.board

import android.support.v4.app.Fragment
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.BoardModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.ChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.board.gallery.BoardFragment
import toothpick.Toothpick

/**
 * Created by milkman on 30.01.18.
 */
class BoardChainFragment : ChainFragment() {

    override fun createChildFragment(screenKey: String, data: Any?): Fragment? = when (screenKey) {
        Screens.BOARD -> BoardFragment()
        else -> null
    }

    override val defaultScreen: String
        get() = Screens.BOARD

    override fun inject() {
        Toothpick.openScopes(Scopes.MAIN_SCREEN, Scopes.BOARD).apply {
            installModules(BoardModule())
            Toothpick.inject(this@BoardChainFragment, this)
        }
    }
}
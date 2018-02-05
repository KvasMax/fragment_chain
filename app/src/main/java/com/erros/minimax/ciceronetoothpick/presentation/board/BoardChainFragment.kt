package com.erros.minimax.ciceronetoothpick.presentation.board

import android.os.Build
import android.support.transition.Explode
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.BoardModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.ChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.board.gallery.BoardFragment
import com.erros.minimax.ciceronetoothpick.presentation.board.picture.PictureFragment
import com.erros.minimax.ciceronetoothpick.presentation.board.picture.PictureTransition
import ru.terrakok.cicerone.commands.Command
import toothpick.Toothpick

/**
 * Created by milkman on 30.01.18.
 */
class BoardChainFragment : ChainFragment() {

    override fun initViews() {
        super.initViews()
        // FIXME without that shared element transition doesn't work
        navigator.preventFragmentDestruction = false
    }

    override fun createChildFragment(screenKey: String, data: Any?): Fragment? = when (screenKey) {
        Screens.BOARD -> BoardFragment()
        Screens.PICTURE -> if (data is String) PictureFragment.getInstance(data) else null
        else -> null
    }

    override fun setupFragmentTransactionAnimation(command: Command, currentFragment: Fragment?, nextFragment: Fragment, fragmentTransaction: FragmentTransaction) {
        if (currentFragment is BoardFragment
                && nextFragment is PictureFragment
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            nextFragment.sharedElementEnterTransition = PictureTransition()
            nextFragment.sharedElementReturnTransition = PictureTransition()
            currentFragment.exitTransition = Explode()

            fragmentTransaction.addSharedElement(currentFragment.clickedView, PictureFragment.TRANSITION_NAME)

        } else {
            super.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction)
        }
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
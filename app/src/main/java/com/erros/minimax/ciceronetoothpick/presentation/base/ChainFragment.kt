package com.erros.minimax.ciceronetoothpick.presentation.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.erros.minimax.ciceronetoothpick.R
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

/**
 * Created by milkman on 29.01.18.
 */
abstract class ChainFragment : BaseFragment(), BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override val layout: Int
        get() = R.layout.fragment_chain

    override fun initViews() {
        if (childFragmentManager.fragments.isEmpty()) {
            router.replaceScreen(defaultScreen)
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = if (childFragmentManager.fragments.isEmpty()) null else childFragmentManager.fragments.last()
        (fragment as? BackButtonListener)?.onBackPressed() ?: router.exit()
    }

    protected val navigator: FragmentChainNavigator by lazy {
        object : FragmentChainNavigator(R.id.contentContainer, childFragmentManager, activity) {

            override fun createFragment(screenKey: String, data: Any?): Fragment? = createChildFragment(screenKey, data)

            override fun setupFragmentTransactionAnimation(command: Command, currentFragment: Fragment?, nextFragment: Fragment, fragmentTransaction: FragmentTransaction) {
                this@ChainFragment.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction)
            }
        }
    }

    protected open fun setupFragmentTransactionAnimation(command: Command, currentFragment: Fragment?, nextFragment: Fragment, fragmentTransaction: FragmentTransaction) {
        when (command) {
            is Forward -> fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            else -> fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }
    }

    protected abstract fun createChildFragment(screenKey: String, data: Any?): Fragment?

    protected abstract val defaultScreen: String

}
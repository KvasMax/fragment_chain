package com.erros.minimax.ciceronetoothpick.presentation.base

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.erros.minimax.ciceronetoothpick.R
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
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
        router.replaceScreen(defaultScreen)
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
        val fragment = childFragmentManager.findFragmentById(R.id.contentContainer)
        if (fragment == null
                || fragment !is BackButtonListener) {
            router.exit()
        } else {
            (fragment as BackButtonListener).onBackPressed()
        }
    }

    private val navigator by lazy {
        object : SupportAppNavigator(activity, childFragmentManager, R.id.contentContainer) {

            override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
                return null//TODO make up smth
            }

            override fun createFragment(screenKey: String?, data: Any?): Fragment? {
                return if (screenKey == null)
                    null
                else createChildFragment(screenKey, data)
            }
        }
    }

    protected abstract fun createChildFragment(screenKey: String, data: Any?): Fragment?

    protected abstract val defaultScreen: String

}
package com.erros.minimax.ciceronetoothpick.presentation.base

import android.support.v4.app.Fragment
import com.erros.minimax.ciceronetoothpick.R
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
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

    private val navigator by lazy {
        object : FragmentChainNavigator(R.id.contentContainer, childFragmentManager, activity) {

            override fun createFragment(screenKey: String, data: Any?): Fragment?
                    = createChildFragment(screenKey, data)

        }
    }

    protected abstract fun createChildFragment(screenKey: String, data: Any?): Fragment?

    protected abstract val defaultScreen: String

}
package com.erros.minimax.ciceronetoothpick.presentation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.qualifier.ScreenChain
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by milkman on 29.01.18.
 */
abstract class ChainFragment : Fragment(), FragmentContainer {

    @Inject
    @ScreenChain
    lateinit var cicerone: Cicerone<Router>

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater?.inflate(R.layout.fragment_chain, container, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, Toothpick.openScope(Scopes.MAIN_ACTIVITY_SCOPE))
    }

    override fun getRouter(): Router = cicerone.router

    override fun getNavigator(): Navigator = navigator

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    private val navigator = object : SupportAppNavigator(activity, childFragmentManager, R.id.contentContainer) {

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent {
            return Intent()
        }

        override fun createFragment(screenKey: String, data: Any?): Fragment {
            return createFragment(screenKey, data)
        }
    }

    protected abstract fun createFragment(screenKey: String, data: Any?): Fragment
}
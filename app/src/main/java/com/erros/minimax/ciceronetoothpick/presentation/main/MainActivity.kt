package com.erros.minimax.ciceronetoothpick.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.MainActivityModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.board.BoardFragment
import com.erros.minimax.ciceronetoothpick.presentation.chat.ConversationChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.history.HistoryChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val conversationChainFragment by lazy { ConversationChainFragment() }
    private val boardFragment by lazy { BoardFragment() }
    private val settingsFragment by lazy { SettingsFragment() }
    private val historyFragment by lazy { HistoryChainFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
        initViews()

    }

    private fun initViews() {
        navigationView.addItem(BottomNavigationItem(android.R.drawable.ic_dialog_map, "Chat"))
                .addItem(BottomNavigationItem(android.R.drawable.ic_dialog_map, "Board"))
                .addItem(BottomNavigationItem(android.R.drawable.ic_dialog_map, "History"))
                .addItem(BottomNavigationItem(android.R.drawable.ic_dialog_map, "Settings"))
                .initialise()

        navigationView.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {

            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                when (position) {
                    TAB_CONVERSATIONS -> presenter.onChatClick()
                    TAB_BOARD -> presenter.onBoardClick()
                    TAB_HISTORY -> presenter.onHistoryClick()
                    TAB_SETTINGS -> presenter.onSettingsClick()
                }
            }
        })
    }

    private fun initPresenter() {
        Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.MAIN_ACTIVITY_SCOPE).apply {
            installModules(MainActivityModule())
            Toothpick.inject(this@MainActivity, this)
        }
        presenter.onViewAttached(this)
    }

    override fun initFragments() {
        supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, conversationChainFragment)
                .add(R.id.contentContainer, boardFragment)
                .add(R.id.contentContainer, settingsFragment)
                .add(R.id.contentContainer, historyFragment)
                .detach(conversationChainFragment)
                .detach(boardFragment)
                .detach(settingsFragment)
                .detach(historyFragment)
                .commitNow()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDetached()
    }

    override fun highlightTab(tabPosition: Int) {
        navigationView.selectTab(tabPosition, false)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    private val navigator = object : Navigator {

        override fun applyCommands(commands: Array<out Command>) {
            for (command in commands) applyCommand(command)
        }

        private fun applyCommand(command: Command) {
            when (command) {
                is Replace -> {
                    when (command.screenKey) {
                        Screens.CONVERSATIONS -> {
                            supportFragmentManager.beginTransaction()
                                    .detach(historyFragment)
                                    .detach(boardFragment)
                                    .detach(settingsFragment)
                                    .attach(conversationChainFragment)
                                    .commitNow()
                        }
                        Screens.BOARD -> {
                            supportFragmentManager.beginTransaction()
                                    .detach(historyFragment)
                                    .detach(settingsFragment)
                                    .detach(conversationChainFragment)
                                    .attach(boardFragment)
                                    .commitNow()
                        }
                        Screens.SETTINGS -> {
                            supportFragmentManager.beginTransaction()
                                    .detach(historyFragment)
                                    .detach(boardFragment)
                                    .detach(conversationChainFragment)
                                    .attach(settingsFragment)
                                    .commitNow()
                        }
                        Screens.GROUPED_HISTORY -> {
                            supportFragmentManager.beginTransaction()
                                    .detach(boardFragment)
                                    .detach(conversationChainFragment)
                                    .detach(settingsFragment)
                                    .attach(historyFragment)
                                    .commitNow()
                        }

                    }
                }
                is Back -> {
                    val fragment = supportFragmentManager.findFragmentById(R.id.contentContainer)
                    if (fragment == null
                            || fragment !is BackButtonListener) {
                        finish()
                    } else {
                        (fragment as BackButtonListener).onBackPressed()
                    }
                }
            }
        }

    }
}

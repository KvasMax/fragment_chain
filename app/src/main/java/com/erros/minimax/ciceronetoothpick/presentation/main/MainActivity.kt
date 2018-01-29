package com.erros.minimax.ciceronetoothpick.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.PresenterModule
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.board.BoardFragment
import com.erros.minimax.ciceronetoothpick.presentation.conversations.ConversationsFragment
import com.erros.minimax.ciceronetoothpick.presentation.history.HistoryFragment
import com.erros.minimax.ciceronetoothpick.presentation.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val conversationsFragment by lazy { ConversationsFragment() }
    private val boardFragment by lazy { BoardFragment() }
    private val settingsFragment by lazy { SettingsFragment() }
    private val historyFragment by lazy { HistoryFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.MAIN_ACTIVITY_SCOPE).apply {
            installModules(PresenterModule())
            Toothpick.inject(this@MainActivity, this)
        }

        presenter.attachView(this)

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

    override fun initFragments() {
        supportFragmentManager.beginTransaction()
                .add(R.id.contentContainer, conversationsFragment)
                .add(R.id.contentContainer, boardFragment)
                .add(R.id.contentContainer, settingsFragment)
                .add(R.id.contentContainer, historyFragment)
                .detach(conversationsFragment)
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
        presenter.detachView()
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
                                    .attach(conversationsFragment)
                                    .commitNow()
                        }
                        Screens.BOARD -> {
                            supportFragmentManager.beginTransaction()
                                    .detach(historyFragment)
                                    .detach(settingsFragment)
                                    .detach(conversationsFragment)
                                    .attach(boardFragment)
                                    .commitNow()
                        }
                        Screens.SETTINGS -> {
                            supportFragmentManager.beginTransaction()
                                    .detach(historyFragment)
                                    .detach(boardFragment)
                                    .detach(conversationsFragment)
                                    .attach(settingsFragment)
                                    .commitNow()
                        }
                        Screens.HISTORY -> {
                            supportFragmentManager.beginTransaction()
                                    .detach(boardFragment)
                                    .detach(conversationsFragment)
                                    .detach(settingsFragment)
                                    .attach(historyFragment)
                                    .commitNow()
                        }

                    }
                }
            }
        }

    }
}

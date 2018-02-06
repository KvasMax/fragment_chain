package com.erros.minimax.ciceronetoothpick.presentation.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.MainActivityModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.board.BoardChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.calendar.CalendarChainFragment
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
    private val boardFragment by lazy { BoardChainFragment() }
    private val settingsFragment by lazy { SettingsFragment() }
    private val historyFragment by lazy { HistoryChainFragment() }
    private val calendarFragment by lazy { CalendarChainFragment() }

    private val fragmentList by lazy { arrayOf(conversationChainFragment, boardFragment, settingsFragment, historyFragment, calendarFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPresenter()
        initFragments()
        initViews()

        presenter.onViewAttached(this)
    }

    private fun initViews() {
        navigationView.addItem(BottomNavigationItem(android.R.drawable.ic_menu_edit, "Chat"))
                .addItem(BottomNavigationItem(android.R.drawable.ic_menu_gallery, "Board"))
                .addItem(BottomNavigationItem(android.R.drawable.ic_menu_recent_history, "History"))
                .addItem(BottomNavigationItem(android.R.drawable.ic_menu_compass, "Settings"))
                .addItem(BottomNavigationItem(android.R.drawable.ic_menu_day, "Calendar"))
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
                    TAB_CALENDAR -> presenter.onCalendarClick()
                }
            }
        })
    }

    private fun initPresenter() {
        Toothpick.openScopes(Scopes.APP, Scopes.MAIN_SCREEN).apply {
            installModules(MainActivityModule())
            Toothpick.inject(this@MainActivity, this)
        }
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction().apply {
            for (fragment in fragmentList) {
                add(R.id.contentContainer, fragment)
                detach(fragment)
            }
        }.commitNow()
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

        private var currentFragment: Fragment? = null

        override fun applyCommands(commands: Array<out Command>) {
            for (command in commands) applyCommand(command)
        }

        private fun applyCommand(command: Command) {
            when (command) {
                is Replace -> {
                    var fragment: Fragment? = null
                    when (command.screenKey) {

                        Screens.CONVERSATIONS -> fragment = conversationChainFragment
                        Screens.BOARD -> fragment = boardFragment
                        Screens.SETTINGS -> fragment = settingsFragment
                        Screens.GROUPED_HISTORY -> fragment = historyFragment
                        Screens.CALENDAR -> fragment = calendarFragment
                    }
                    fragment?.let {
                        if (currentFragment != it) {
                            val transaction = supportFragmentManager.beginTransaction()
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)

                            fragmentList.filter { f -> f.isResumed }.forEach {
                                transaction.hide(it)
                            }
                            if (it.isAdded) {
                                transaction.show(it)
                            } else {
                                transaction.attach(it)
                            }
                            transaction.commitNow()
                            currentFragment = it
                        }
                    }
                }
                is Back -> {
                    (currentFragment as? BackButtonListener)?.onBackPressed() ?: finish()
                }
            }
        }

    }
}

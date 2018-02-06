package com.erros.minimax.ciceronetoothpick.presentation.main

import com.erros.minimax.ciceronetoothpick.presentation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by minimax on 1/28/18.
 */
class MainPresenter
@Inject constructor(private val route: Router)
    : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun onViewAttached(view: MainContract.View) {
        this.view = view
        onChatClick()
    }

    override fun onChatClick() {
        route.replaceScreen(Screens.CONVERSATIONS)
        view?.apply {
            highlightTab(TAB_CONVERSATIONS)
        }
    }

    override fun onBoardClick() {
        route.replaceScreen(Screens.BOARD)
        view?.apply {
            highlightTab(TAB_BOARD)
        }
    }

    override fun onHistoryClick() {
        route.replaceScreen(Screens.GROUPED_HISTORY)
        view?.apply {
            highlightTab(TAB_HISTORY)
        }
    }

    override fun onSettingsClick() {
        route.replaceScreen(Screens.SETTINGS)
        view?.apply {
            highlightTab(TAB_SETTINGS)
        }
    }

    override fun onCalendarClick() {
        route.replaceScreen(Screens.CALENDAR)
        view?.apply {
            highlightTab(TAB_CALENDAR)
        }
    }

    override fun onBackPressed() {
        route.exit()
    }

    override fun onViewDetached() {
        view = null
    }

    override fun onCreate() {
    }

    override fun onDestroy() {
    }
}
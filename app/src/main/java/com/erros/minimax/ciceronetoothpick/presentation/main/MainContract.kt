package com.erros.minimax.ciceronetoothpick.presentation.main

import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by minimax on 1/28/18.
 */
interface MainContract {

    interface View : BaseView {
        val TAB_CONVERSATIONS get() = 0
        val TAB_BOARD get() = 1
        val TAB_HISTORY get() = 2
        val TAB_SETTINGS get() = 3

        fun highlightTab(tabPosition: Int)
        fun initFragments()
    }

    interface Presenter : BasePresenter<View> {
        fun onChatClick()
        fun onBoardClick()
        fun onHistoryClick()
        fun onSettingsClick()
        fun onBackPressed()
    }

}
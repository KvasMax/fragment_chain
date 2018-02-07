package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import com.erros.minimax.ciceronetoothpick.data.model.Person
import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by milkman on 30.01.18.
 */
interface ConversationsContract {

    interface View : BaseView {
        fun showProgress(show: Boolean)
        fun showUsers(persons: List<Person>)
    }

    interface Presenter : BasePresenter<View>, BackButtonListener {
        fun onOpenChatClick()
        fun onRefresh()
    }

}
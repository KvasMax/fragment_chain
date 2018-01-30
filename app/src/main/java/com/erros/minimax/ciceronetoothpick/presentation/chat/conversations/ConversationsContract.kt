package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by milkman on 30.01.18.
 */
interface ConversationsContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View>, BackButtonListener {
        fun onOpenChatClick()
    }

}
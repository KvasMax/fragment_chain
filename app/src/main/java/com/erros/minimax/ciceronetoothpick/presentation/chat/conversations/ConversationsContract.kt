package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by milkman on 30.01.18.
 */
interface ConversationsContract {

    interface View : BaseView {

    }

    interface Presenter : com.erros.minimax.ciceronetoothpick.presentation.base.Presenter<View>, BackButtonListener {
        fun onOpenChatClick()
    }

}
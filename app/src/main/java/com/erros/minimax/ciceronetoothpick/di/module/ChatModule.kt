package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.presentation.chat.conversations.ConversationsContract
import com.erros.minimax.ciceronetoothpick.presentation.chat.conversations.ConversationsPresenter
import com.erros.minimax.ciceronetoothpick.presentation.chat.messages.ChatContract
import com.erros.minimax.ciceronetoothpick.presentation.chat.messages.ChatPresenter

/**
 * Created by minimax on 1/28/18.
 */
class ChatModule : NavigationModule() {

    init {
        bind(ConversationsContract.Presenter::class.java).to(ConversationsPresenter::class.java).singletonInScope()
        bind(ChatContract.Presenter::class.java).to(ChatPresenter::class.java).instancesInScope()
    }

}
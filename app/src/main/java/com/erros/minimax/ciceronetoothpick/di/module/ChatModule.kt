package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.presentation.chat.conversations.ConversationsContract
import com.erros.minimax.ciceronetoothpick.presentation.chat.conversations.ConversationsPresenter
import com.erros.minimax.ciceronetoothpick.presentation.chat.messages.ChatContract
import com.erros.minimax.ciceronetoothpick.presentation.chat.messages.ChatPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * Created by minimax on 1/28/18.
 */
class ChatModule : Module() {

    init {
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        bind(ConversationsContract.Presenter::class.java).to(ConversationsPresenter::class.java).singletonInScope()
        bind(ChatContract.Presenter::class.java).to(ChatPresenter::class.java).instancesInScope()
    }

}
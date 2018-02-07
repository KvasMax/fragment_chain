package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.data.network.PlaceholderApi
import com.erros.minimax.ciceronetoothpick.data.repository.PlaceholderRepository
import com.erros.minimax.ciceronetoothpick.di.providers.PlaceholderApiProvider
import com.erros.minimax.ciceronetoothpick.di.providers.PlaceholderPathProvider
import com.erros.minimax.ciceronetoothpick.di.qualifier.PlaceholderPath
import com.erros.minimax.ciceronetoothpick.presentation.chat.conversations.ConversationsContract
import com.erros.minimax.ciceronetoothpick.presentation.chat.conversations.ConversationsPresenter
import com.erros.minimax.ciceronetoothpick.presentation.chat.messages.ChatContract
import com.erros.minimax.ciceronetoothpick.presentation.chat.messages.ChatPresenter

/**
 * Created by minimax on 1/28/18.
 */
class ChatModule : NavigationModule() {

    init {
        bind(String::class.java).withName(PlaceholderPath::class.java).toProvider(PlaceholderPathProvider::class.java).providesSingletonInScope()
        bind(PlaceholderApi::class.java).toProvider(PlaceholderApiProvider::class.java).providesSingletonInScope()
        bind(PlaceholderRepository::class.java).singletonInScope()

        bind(ConversationsContract.Presenter::class.java).to(ConversationsPresenter::class.java).singletonInScope()
        bind(ChatContract.Presenter::class.java).to(ChatPresenter::class.java).instancesInScope()
    }

}
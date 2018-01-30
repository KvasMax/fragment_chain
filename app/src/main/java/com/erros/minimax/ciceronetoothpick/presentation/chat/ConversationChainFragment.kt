package com.erros.minimax.ciceronetoothpick.presentation.chat

import android.support.v4.app.Fragment
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.ChatModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.ChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.chat.conversations.ConversationsFragment
import com.erros.minimax.ciceronetoothpick.presentation.chat.messages.ChatFragment
import toothpick.Toothpick

/**
 * Created by milkman on 30.01.18.
 */
class ConversationChainFragment : ChainFragment() {

    override fun createChildFragment(screenKey: String, data: Any?): Fragment? = when (screenKey) {
        Screens.CONVERSATIONS -> ConversationsFragment()
        Screens.CHAT -> ChatFragment()
        else -> null
    }

    override val defaultScreen: String
        get() = Screens.CONVERSATIONS

    override fun inject() {
        Toothpick.openScopes(Scopes.MAIN_SCREEN, Scopes.CHAT).apply {
            installModules(ChatModule())
            Toothpick.inject(this@ConversationChainFragment, this)
        }
    }
}
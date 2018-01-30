package com.erros.minimax.ciceronetoothpick.presentation.chain

import android.support.v4.app.Fragment
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.ChatModule
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.ChainFragment
import com.erros.minimax.ciceronetoothpick.presentation.chat.ChatFragment
import com.erros.minimax.ciceronetoothpick.presentation.conversations.ConversationsFragment
import toothpick.Toothpick

/**
 * Created by milkman on 30.01.18.
 */
class ConversationChainFragment : ChainFragment() {

    override fun createChildFragment(screenKey: String, data: Any?): Fragment = when (screenKey) {
        Screens.CONVERSATIONS -> ConversationsFragment()
        Screens.CHAT -> ChatFragment()
        else -> ConversationsFragment()
    }

    override val defaultScreen: String
        get() = Screens.CONVERSATIONS

    override fun inject() {
        Toothpick.openScope(Scopes.CHAT_SCOPE).apply {
            installModules(ChatModule())
            Toothpick.inject(this@ConversationChainFragment, this)
        }
    }
}
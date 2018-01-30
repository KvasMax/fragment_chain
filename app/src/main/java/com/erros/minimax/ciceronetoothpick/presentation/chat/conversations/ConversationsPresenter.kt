package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class ConversationsPresenter
@Inject constructor(private val router: Router)
    : BasePresenter<ConversationsContract.View>(), ConversationsContract.Presenter {

    override fun onOpenChatClick() {
        router.navigateTo(Screens.CHAT)
    }

    override fun onBackPressed() {
        router.exit()
    }

}
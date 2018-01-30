package com.erros.minimax.ciceronetoothpick.presentation.conversations

import com.erros.minimax.ciceronetoothpick.presentation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class ConversationsPresenter
@Inject constructor(private val router: Router)
    : ConversationsContract.Presenter {

    private var view: ConversationsContract.View? = null

    override fun attachView(view: ConversationsContract.View) {
        this.view = view
    }

    override fun onOpenChatClick() {
        router.navigateTo(Screens.CHAT)
    }

    override fun detachView() {
        this.view = null
    }

    override fun onBackPressed() {
        router.exit()
    }
}
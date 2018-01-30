package com.erros.minimax.ciceronetoothpick.presentation.chat.messages

import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class ChatPresenter
@Inject constructor(private val router: Router)
    : ChatContract.Presenter {

    private var view: ChatContract.View? = null

    override fun attachView(view: ChatContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun onBackPressed() {
        router.exit()
    }
}
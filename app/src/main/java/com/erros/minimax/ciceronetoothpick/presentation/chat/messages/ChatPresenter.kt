package com.erros.minimax.ciceronetoothpick.presentation.chat.messages

import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class ChatPresenter
@Inject constructor(private val router: Router)
    : BasePresenter<ChatContract.View>(), ChatContract.Presenter {

    override fun onBackPressed() {
        router.exit()
    }

}
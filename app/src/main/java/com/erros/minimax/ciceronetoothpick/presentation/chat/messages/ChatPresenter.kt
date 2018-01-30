package com.erros.minimax.ciceronetoothpick.presentation.chat.messages

import com.erros.minimax.ciceronetoothpick.presentation.base.AbstractBasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class ChatPresenter
@Inject constructor(private val router: Router)
    : AbstractBasePresenter<ChatContract.View>(), ChatContract.Presenter {

    override fun onBackPressed() {
        router.exit()
    }

}
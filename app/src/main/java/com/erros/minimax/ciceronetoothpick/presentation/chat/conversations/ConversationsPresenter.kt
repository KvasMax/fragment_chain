package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import com.erros.minimax.ciceronetoothpick.data.repository.PlaceholderRepository
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.AbstractBasePresenter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class ConversationsPresenter
@Inject constructor(private val router: Router,
                    private val placeholderRepository: PlaceholderRepository)
    : AbstractBasePresenter<ConversationsContract.View>(), ConversationsContract.Presenter {

    override fun onViewAttached(view: ConversationsContract.View) {
        super.onViewAttached(view)
        loadData()
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        launch(UI) {
            try {
                view?.showProgress(true)
                delay(1, TimeUnit.SECONDS)
                view?.showUsers(placeholderRepository.getUsers().await())
            } catch (ex: Exception) {
                handleError(ex)
                router.showSystemMessage(ex.message)
            } finally {
                view?.showProgress(false)
            }
        }
    }

    override fun onOpenChatClick() {
        router.navigateTo(Screens.CHAT)
    }

    override fun onBackPressed() {
        router.exit()
    }

}
package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import com.erros.minimax.ciceronetoothpick.data.repository.PlaceholderRepository
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.AbstractBasePresenter
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
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

    private var job: Job? = null

    override fun onViewAttached(view: ConversationsContract.View) {
        super.onViewAttached(view)
        loadData()
    }

    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        job?.cancel()
        job = async(UI) {
            view?.showProgress(true)
            delay(1, TimeUnit.SECONDS)
            view?.showUsers(placeholderRepository.getUsers().await())
        }
        job?.invokeOnCompletion {
            view?.showProgress(false)
            it?.let {
                handleError(it)
                router.showSystemMessage(it.message)
            }
        }
    }

    override fun onOpenChatClick() {
        router.navigateTo(Screens.CHAT)
    }

    override fun onBackPressed() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
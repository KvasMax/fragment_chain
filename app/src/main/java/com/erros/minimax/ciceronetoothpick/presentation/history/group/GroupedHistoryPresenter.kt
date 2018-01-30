package com.erros.minimax.ciceronetoothpick.presentation.history.group

import com.erros.minimax.ciceronetoothpick.presentation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class GroupedHistoryPresenter
@Inject constructor(private val router: Router)
    : GroupedHistoryContract.Presenter {

    private var view: GroupedHistoryContract.View? = null

    override fun attachView(view: GroupedHistoryContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onOpenDetailClick() {
        router.navigateTo(Screens.DETAIL_HISTORY)
    }

    override fun onBackPressed() {
        router.exit()
    }
}
package com.erros.minimax.ciceronetoothpick.presentation.history.group

import com.erros.minimax.ciceronetoothpick.presentation.Screens
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class GroupedHistoryPresenter
@Inject constructor(private val router: Router)
    : BasePresenter<GroupedHistoryContract.View>(), GroupedHistoryContract.Presenter {

    override fun onOpenDetailClick() {
        router.navigateTo(Screens.DETAIL_HISTORY)
    }

    override fun onBackPressed() {
        router.exit()
    }

}
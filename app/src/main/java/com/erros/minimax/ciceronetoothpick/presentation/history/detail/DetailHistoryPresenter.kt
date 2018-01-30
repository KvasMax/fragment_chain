package com.erros.minimax.ciceronetoothpick.presentation.history.detail

import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class DetailHistoryPresenter
@Inject constructor(private val router: Router)
    : BasePresenter<DetailHistoryContract.View>(), DetailHistoryContract.Presenter {

    override fun onBackPressed() {
        router.exit()
    }
}
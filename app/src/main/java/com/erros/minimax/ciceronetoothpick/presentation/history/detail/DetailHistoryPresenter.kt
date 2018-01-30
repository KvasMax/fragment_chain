package com.erros.minimax.ciceronetoothpick.presentation.history.detail

import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 30.01.18.
 */
class DetailHistoryPresenter
@Inject constructor(private val router: Router)
    : DetailHistoryContract.Presenter {

    private var view: DetailHistoryContract.View? = null

    override fun onViewAttached(view: DetailHistoryContract.View) {
        this.view = view
    }

    override fun onViewDetached() {
        this.view = null
    }

    override fun onBackPressed() {
        router.exit()
    }

    override fun onCreate() {
    }

    override fun onDestroy() {
    }
}
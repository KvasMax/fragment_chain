package com.erros.minimax.ciceronetoothpick.presentation.history.detail

import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by milkman on 30.01.18.
 */
interface DetailHistoryContract {

    interface View : BaseView {

    }

    interface Presenter : com.erros.minimax.ciceronetoothpick.presentation.base.Presenter<View>, BackButtonListener {
    }

}
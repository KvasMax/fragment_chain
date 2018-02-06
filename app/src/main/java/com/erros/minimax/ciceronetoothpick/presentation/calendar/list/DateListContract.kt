package com.erros.minimax.ciceronetoothpick.presentation.calendar.list

import com.erros.minimax.ciceronetoothpick.data.model.Day
import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by milkman on 06.02.18.
 */
interface DateListContract {

    interface View : BaseView {
        fun showData(show: Boolean, data: List<Day>)
        fun showEmptyProgress(show: Boolean)
        fun showRefreshProgress(show: Boolean)
    }

    interface Presenter : BasePresenter<View>, BackButtonListener {
        fun onLoadNext()
        fun onRefresh()
    }

}
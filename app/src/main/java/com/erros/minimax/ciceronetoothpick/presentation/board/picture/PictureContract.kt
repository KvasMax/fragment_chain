package com.erros.minimax.ciceronetoothpick.presentation.board.picture

import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by milkman on 05.02.18.
 */
interface PictureContract {

    interface View : BaseView {
        fun showPicture(url: String)
    }

    interface Presenter : BasePresenter<View>, BackButtonListener {
    }
}
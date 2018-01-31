package com.erros.minimax.ciceronetoothpick.presentation.board.gallery

import com.erros.minimax.ciceronetoothpick.Picture
import com.erros.minimax.ciceronetoothpick.presentation.base.BackButtonListener
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenter
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseView

/**
 * Created by milkman on 31.01.18.
 */
interface BoardContract {

    interface View : BaseView {
        fun addImageToList(picture: Picture)
    }

    interface Presenter : BasePresenter<View>, BackButtonListener {

    }

}
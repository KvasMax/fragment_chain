package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.di.qualifier.PictureUrl
import com.erros.minimax.ciceronetoothpick.presentation.board.picture.PictureContract
import com.erros.minimax.ciceronetoothpick.presentation.board.picture.PicturePresenter
import toothpick.config.Module

/**
 * Created by milkman on 05.02.18.
 */
class PictureModule
constructor(pictureUrl: String)
    : Module() {

    init {
        bind(String::class.java).withName(PictureUrl::class.java).toInstance(pictureUrl)
        bind(PictureContract.Presenter::class.java).to(PicturePresenter::class.java).singletonInScope()
    }

}
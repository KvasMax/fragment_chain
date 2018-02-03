package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.data.network.PicsumApi
import com.erros.minimax.ciceronetoothpick.data.repository.PictureRepository
import com.erros.minimax.ciceronetoothpick.di.providers.PicServerPathProvider
import com.erros.minimax.ciceronetoothpick.di.providers.PicsumApiProvider
import com.erros.minimax.ciceronetoothpick.di.qualifier.PicServerPath
import com.erros.minimax.ciceronetoothpick.presentation.board.gallery.BoardContract
import com.erros.minimax.ciceronetoothpick.presentation.board.gallery.BoardPresenter

/**
 * Created by milkman on 31.01.18.
 */
class BoardModule : NavigationModule() {

    init {
        bind(String::class.java).withName(PicServerPath::class.java).toProvider(PicServerPathProvider::class.java).providesSingletonInScope()
        bind(PicsumApi::class.java).toProvider(PicsumApiProvider::class.java).providesSingletonInScope()
        bind(PictureRepository::class.java).singletonInScope()
        bind(BoardContract.Presenter::class.java).to(BoardPresenter::class.java).singletonInScope()
    }

}
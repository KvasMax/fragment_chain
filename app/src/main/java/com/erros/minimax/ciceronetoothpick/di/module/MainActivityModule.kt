package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.presentation.main.MainContract
import com.erros.minimax.ciceronetoothpick.presentation.main.MainPresenter

/**
 * Created by minimax on 1/28/18.
 */
class MainActivityModule : NavigationModule() {

    init {
        bind(MainContract.Presenter::class.java).to(MainPresenter::class.java).singletonInScope()
    }

}
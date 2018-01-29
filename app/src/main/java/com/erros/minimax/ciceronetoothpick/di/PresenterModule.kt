package com.erros.minimax.ciceronetoothpick.di

import com.erros.minimax.ciceronetoothpick.di.providers.CiceroneProvider
import com.erros.minimax.ciceronetoothpick.di.qualifier.ScreenChain
import com.erros.minimax.ciceronetoothpick.presentation.main.MainContract
import com.erros.minimax.ciceronetoothpick.presentation.main.MainPresenter
import ru.terrakok.cicerone.Cicerone
import toothpick.config.Module

/**
 * Created by minimax on 1/28/18.
 */
class PresenterModule: Module() {

    init {
        bind(MainContract.Presenter::class.java).to(MainPresenter::class.java)
        bind(Cicerone::class.java).withName(ScreenChain::class.java).toProviderInstance(CiceroneProvider())
    }

}
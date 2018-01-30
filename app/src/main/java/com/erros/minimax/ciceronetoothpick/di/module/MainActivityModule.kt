package com.erros.minimax.ciceronetoothpick.di.module

import com.erros.minimax.ciceronetoothpick.presentation.main.MainContract
import com.erros.minimax.ciceronetoothpick.presentation.main.MainPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * Created by minimax on 1/28/18.
 */
class MainActivityModule : Module() {

    init {
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)

        bind(MainContract.Presenter::class.java).to(MainPresenter::class.java).singletonInScope()
    }

}
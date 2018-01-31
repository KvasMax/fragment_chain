package com.erros.minimax.ciceronetoothpick.di.module

import android.content.Context
import com.erros.minimax.ciceronetoothpick.presentation.main.MainContract
import com.erros.minimax.ciceronetoothpick.presentation.main.MainPresenter

/**
 * Created by minimax on 1/28/18.
 */
class MainActivityModule(context: Context) : NavigationModule() {

    init {
        bind(Context::class.java).toInstance(context)
        bind(MainContract.Presenter::class.java).to(MainPresenter::class.java).singletonInScope()
    }

}
package com.erros.minimax.ciceronetoothpick.di

import android.content.Context
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

/**
 * Created by minimax on 1/28/18.
 */
class AppModule(context: Context): Module() {

    init {
        bind(Context::class.java).toInstance(context.applicationContext)

        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }


}
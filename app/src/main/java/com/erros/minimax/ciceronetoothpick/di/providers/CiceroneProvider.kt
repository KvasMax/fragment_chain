package com.erros.minimax.ciceronetoothpick.di.providers

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by milkman on 29.01.18.
 */
class CiceroneProvider @Inject constructor() : Provider<Cicerone<Router>> {

    override fun get(): Cicerone<Router> = Cicerone.create()
}
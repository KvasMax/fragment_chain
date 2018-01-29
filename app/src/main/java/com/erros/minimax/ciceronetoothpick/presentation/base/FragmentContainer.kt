package com.erros.minimax.ciceronetoothpick.presentation.base

import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * Created by milkman on 29.01.18.
 */
interface FragmentContainer {

    fun getRouter(): Router
    fun getNavigator(): Navigator

}
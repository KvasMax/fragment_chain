package com.erros.minimax.ciceronetoothpick.presentation.base

/**
 * Created by minimax on 1/28/18.
 */
interface Presenter<in V : BaseView> {

    fun onCreate()

    fun onViewAttached(view: V)

    fun onViewDetached()

    fun onDestroy()

}
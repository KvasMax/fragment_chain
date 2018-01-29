package com.erros.minimax.ciceronetoothpick.presentation.base

/**
 * Created by minimax on 1/28/18.
 */
interface BasePresenter<in V : BaseView> {

    fun attachView(view:V)

    fun detachView()

}
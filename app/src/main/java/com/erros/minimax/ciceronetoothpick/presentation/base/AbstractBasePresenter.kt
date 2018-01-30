package com.erros.minimax.ciceronetoothpick.presentation.base

/**
 * Created by milkman on 30.01.18.
 */
abstract class AbstractBasePresenter<V : BaseView> : BasePresenter<V> {

    protected var view: V? = null

    override fun onCreate() {
    }

    override fun onViewAttached(view: V) {
        this.view = view
    }

    override fun onViewDetached() {
        view = null
    }

    override fun onDestroy() {
    }
}
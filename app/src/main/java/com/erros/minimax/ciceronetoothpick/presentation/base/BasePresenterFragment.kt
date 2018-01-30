package com.erros.minimax.ciceronetoothpick.presentation.base

import android.os.Bundle
import android.view.View

/**
 * Created by milkman on 30.01.18.
 */
abstract class BasePresenterFragment<out P : Presenter<V>, in V : BaseView> : BaseFragment(), BackButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basePresenter.onCreate()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basePresenter.onViewAttached(getPresenterView())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        basePresenter.onViewDetached()
    }

    override fun onDestroy() {
        super.onDestroy()
        basePresenter.onDestroy()
    }

    private fun getPresenterView(): V = this as V

    protected abstract val basePresenter: P

}
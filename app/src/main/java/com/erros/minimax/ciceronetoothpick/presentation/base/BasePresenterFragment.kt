package com.erros.minimax.ciceronetoothpick.presentation.base

import android.os.Bundle

/**
 * Created by milkman on 30.01.18.
 */
abstract class BasePresenterFragment : BaseFragment(), BackButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachPresenter()
    }

    protected abstract fun attachPresenter()

    protected abstract fun detachPresenter()
}
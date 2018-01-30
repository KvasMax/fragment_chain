package com.erros.minimax.ciceronetoothpick.presentation.base

import android.os.Bundle
import android.util.Log
import android.view.View

/**
 * Created by milkman on 30.01.18.
 */
abstract class BasePresenterFragment<out P : Presenter<V>, in V : BaseView> : BaseFragment(), BackButtonListener {

    private val TAG = "FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basePresenter.onCreate()
        Log.d(TAG, "onCreate " + javaClass.simpleName)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basePresenter.onViewAttached(presenterView)
        Log.d(TAG, "onViewCreated " + javaClass.simpleName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        basePresenter.onViewDetached()
        Log.d(TAG, "onDestroyView " + javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        basePresenter.onDestroy()
        Log.d(TAG, "onDestroy " + javaClass.simpleName)
    }

    private val presenterView: V = this as V

    protected abstract val basePresenter: P

}
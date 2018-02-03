package com.erros.minimax.ciceronetoothpick.presentation.base

import android.os.Bundle
import android.view.View
import timber.log.Timber

/**
 * Created by milkman on 30.01.18.
 */
abstract class BasePresenterFragment<out P : BasePresenter<V>, in V : BaseView> : BaseFragment(), BackButtonListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate ${javaClass.simpleName}")
        basePresenter.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated ${javaClass.simpleName}")
        basePresenter.onViewAttached(presenterView)
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume ${javaClass.simpleName}")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause ${javaClass.simpleName}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView ${javaClass.simpleName}")
        basePresenter.onViewDetached()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy ${javaClass.simpleName}")
        basePresenter.onDestroy()
    }

    private val presenterView: V = this as V

    protected abstract val basePresenter: P

}
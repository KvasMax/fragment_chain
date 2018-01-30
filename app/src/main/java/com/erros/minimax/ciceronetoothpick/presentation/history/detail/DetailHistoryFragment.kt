package com.erros.minimax.ciceronetoothpick.presentation.history.detail

import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment.*
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by minimax on 1/28/18.
 */
class DetailHistoryFragment : BasePresenterFragment<DetailHistoryContract.Presenter, DetailHistoryContract.View>(), DetailHistoryContract.View {

    @Inject
    lateinit var presenter: DetailHistoryContract.Presenter

    override val layout: Int
        get() = R.layout.fragment

    override fun initViews() {
        textView.text = "Detail history"
    }

    override fun inject() {
        Toothpick.inject(this, Toothpick.openScope(Scopes.HISTORY_SCOPE))
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override val basePresenter: DetailHistoryContract.Presenter
        get() = presenter
}
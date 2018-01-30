package com.erros.minimax.ciceronetoothpick.presentation.history.group

import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment.*
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by minimax on 1/28/18.
 */
class GroupedHistoryFragment : BasePresenterFragment(), GroupedHistoryContract.View {

    @Inject
    lateinit var presenter: GroupedHistoryContract.Presenter

    override val layout: Int
        get() = R.layout.fragment

    override fun initViews() {
        textView.text = "Grouped history"
        textView.setOnClickListener {
            presenter.onOpenDetailClick()
        }
    }

    override fun inject() {
        Toothpick.inject(this, Toothpick.openScope(Scopes.HISTORY_SCOPE))
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun attachPresenter() {
        presenter.attachView(this)
    }

    override fun detachPresenter() {
        presenter.detachView()
    }
}
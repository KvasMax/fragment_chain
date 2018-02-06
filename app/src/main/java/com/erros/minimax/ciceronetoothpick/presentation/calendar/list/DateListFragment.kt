package com.erros.minimax.ciceronetoothpick.presentation.calendar.list

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.data.model.Day
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.extension.visible
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment_calendar.*
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by milkman on 06.02.18.
 */
class DateListFragment : BasePresenterFragment<DateListContract.Presenter, DateListContract.View>(), DateListContract.View {

    @Inject
    lateinit var presenter: DateListContract.Presenter

    override val layout: Int
        get() = R.layout.fragment_calendar

    private val adapter by lazy {
        DayAdapter({
            presenter.onRefresh()
        })
    }

    override fun initViews() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if ((recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == adapter.itemCount - 1) {
                    presenter.onLoadNext()
                }
            }
        })
    }

    override fun showData(show: Boolean, data: List<Day>) {
        recyclerView.visible(show)
        adapter.setData(data)
    }

    override fun showEmptyProgress(show: Boolean) {
        progressBar.visible(show)
        swipeRefreshLayout.visible(!show)
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showRefreshProgress(show: Boolean) {
        swipeRefreshLayout.isRefreshing = show
    }

    override fun showEmpty(show: Boolean) {
        adapter.showEmpty(show)
    }

    override fun showEmptyError(show: Boolean) {
        adapter.showError(show)
    }

    override fun showPageProgress(show: Boolean) {
        adapter.showProgress(show)
    }

    override fun inject() {
        Toothpick.inject(this, Toothpick.openScope(Scopes.CALENDAR))
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override val basePresenter: DateListContract.Presenter
        get() = presenter
}
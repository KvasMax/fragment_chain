package com.erros.minimax.ciceronetoothpick.presentation.calendar.list

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.data.model.Day
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.extension.visible
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment_calendar.*
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.ex.loadmore.SlimMoreLoader
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
        SlimAdapter.create()
                .register<Day>(R.layout.item_day, { day, injector ->
                    injector.text(R.id.date_text_view, day.date)
                            .text(R.id.title_text_view, day.celebrations.joinToString(separator = "\n") { it.title })
                            .with<View>(R.id.colorIndicatorView, {
                                var color = day.celebrations.first().colour
                                if (color == "violet") {
                                    color = "purple"
                                }
                                it.setBackgroundColor(Color.parseColor(color))
                            })
                })
                .enableLoadMore(object : SlimMoreLoader(activity) {
                    override fun hasMore(): Boolean = true

                    override fun onLoadMore(handler: Handler?) {
                        presenter.onLoadNext()
                    }
                })
    }

    override fun initViews() {
        adapter.attachTo(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
        /* recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
             override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                 if ((recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == adapter.itemCount - 1) {
                     presenter.onLoadNext()
                 }
             }
         })*/
    }

    override fun showData(show: Boolean, data: List<Day>) {
        recyclerView.visible(show)
        adapter.updateData(data)
    }

    override fun showEmptyProgress(show: Boolean) {
        progressBar.visible(show)
    }

    override fun showRefreshProgress(show: Boolean) {
        swipeRefreshLayout.isRefreshing = show
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
package com.erros.minimax.ciceronetoothpick.presentation.calendar.list

import com.erros.minimax.ciceronetoothpick.data.model.Day
import com.erros.minimax.ciceronetoothpick.data.repository.CalendarRepository
import com.erros.minimax.ciceronetoothpick.presentation.base.AbstractBasePresenter
import com.erros.minimax.ciceronetoothpick.presentation.base.Paginator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import java.util.*
import javax.inject.Inject

/**
 * Created by milkman on 06.02.18.
 */
class DateListPresenter
@Inject constructor(private val router: Router,
                    private val calendarRepository: CalendarRepository)
    : AbstractBasePresenter<DateListContract.View>(), DateListContract.Presenter, Paginator.ViewController<Day> {

    private val paginator = Paginator({ year, month ->
        calendarRepository.getMonthlyCalendar(year, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    },
            this, Date())

    override fun onViewAttached(view: DateListContract.View) {
        super.onViewAttached(view)
        onRefresh()
    }

    override fun onLoadNext() {
        paginator.loadNewPage()
    }

    override fun onRefresh() {
        paginator.refresh()
    }

    override fun onEmptyProgress(show: Boolean) {
        view?.showEmptyProgress(show)
    }

    override fun onEmptyError(show: Boolean, error: Throwable?) {
        error?.let {
            handleError(it)
        }

    }

    override fun onEmptyView(show: Boolean) {
    }

    override fun onData(show: Boolean, data: List<Day>) {
        if (show) {
            view?.showData(show, data)
        }
    }

    override fun onErrorMessage(error: Throwable) {
    }

    override fun onRefreshProgress(show: Boolean) {
        view?.showRefreshProgress(show)
    }

    override fun onPageProgress(show: Boolean) {
    }

    override fun onBackPressed() {
        router.exit()
    }
}
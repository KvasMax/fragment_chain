package com.erros.minimax.ciceronetoothpick.presentation.base

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.*

/**
 * Created by Konstantin Tskhovrebov (aka @terrakok) on 22.07.17.
 */
class Paginator<T>(
        private val requestFactory: (Int, Int) -> Single<List<T>>,
        private val viewController: ViewController<T>,
        initialDate: Date
) {

    interface ViewController<T> {
        fun onEmptyProgress(show: Boolean)
        fun onEmptyError(show: Boolean, error: Throwable? = null)
        fun onEmptyView(show: Boolean)
        fun onData(show: Boolean, data: List<T> = emptyList())
        fun onErrorMessage(error: Throwable)
        fun onRefreshProgress(show: Boolean)
        fun onPageProgress(show: Boolean)
    }

    private val startDate: Date = initialDate.clone() as Date
    private val calendar = Calendar.getInstance()

    private var currentState: State<T> = EMPTY()
    private var currentDate: Date = startDate.clone() as Date
    private val currentData = mutableListOf<T>()
    private var disposable: Disposable? = null

    fun restart() {
        currentState.restart()
    }

    fun refresh() {
        currentState.refresh()
    }

    fun loadNewPage() {
        currentState.loadNewPage()
    }

    fun release() {
        currentState.release()
    }

    private fun loadPage(date: Date) {
        calendar.time = date
        disposable?.dispose()
        disposable = requestFactory.invoke(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
                .subscribe(
                        { currentState.newData(it) },
                        { currentState.fail(it) }
                )
    }

    private interface State<T> {
        fun restart() {}
        fun refresh() {}
        fun loadNewPage() {}
        fun release() {}
        fun newData(data: List<T>) {}
        fun fail(error: Throwable) {}
    }

    private inner class EMPTY : State<T> {

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class EMPTY_PROGRESS : State<T> {

        override fun restart() {
            loadPage(startDate)
        }

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.clear()
                currentData.addAll(data)
                currentDate = startDate.clone() as Date
                viewController.onEmptyProgress(false)
                viewController.onData(true, currentData)
            } else {
                currentState = EMPTY_DATA()
                viewController.onEmptyProgress(false)
                viewController.onEmptyView(true)
            }
        }

        override fun fail(error: Throwable) {
            currentState = EMPTY_ERROR()
            viewController.onEmptyProgress(false)
            viewController.onEmptyError(true, error)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class EMPTY_ERROR : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.onEmptyError(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            viewController.onEmptyError(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class EMPTY_DATA : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.onEmptyView(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun refresh() {
            currentState = EMPTY_PROGRESS()
            viewController.onEmptyView(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class DATA : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.onData(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun refresh() {
            currentState = REFRESH()
            viewController.onRefreshProgress(true)
            loadPage(startDate)
        }

        override fun loadNewPage() {
            currentState = PAGE_PROGRESS()
            viewController.onPageProgress(true)

            calendar.time = currentDate
            calendar.add(Calendar.MONTH, 1)

            loadPage(calendar.time)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class REFRESH : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.onData(false)
            viewController.onRefreshProgress(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.clear()
                currentData.addAll(data)
                currentDate = startDate.clone() as Date
                viewController.onRefreshProgress(false)
                viewController.onData(true, currentData)
            } else {
                currentState = EMPTY_DATA()
                currentData.clear()
                viewController.onData(false)
                viewController.onRefreshProgress(false)
                viewController.onEmptyView(true)
            }
        }

        override fun fail(error: Throwable) {
            currentState = DATA()
            viewController.onRefreshProgress(false)
            viewController.onErrorMessage(error)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class PAGE_PROGRESS : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.onData(false)
            viewController.onPageProgress(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun newData(data: List<T>) {
            if (data.isNotEmpty()) {
                currentState = DATA()
                currentData.addAll(data)

                calendar.time = currentDate
                calendar.add(Calendar.MONTH, 1)
                currentDate = calendar.time

                viewController.onPageProgress(false)
                viewController.onData(true, currentData)
            } else {
                currentState = ALL_DATA()
                viewController.onPageProgress(false)
            }
        }

        override fun refresh() {
            currentState = REFRESH()
            viewController.onPageProgress(false)
            viewController.onRefreshProgress(true)
            loadPage(startDate)
        }

        override fun fail(error: Throwable) {
            currentState = DATA()
            viewController.onPageProgress(false)
            viewController.onErrorMessage(error)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class ALL_DATA : State<T> {

        override fun restart() {
            currentState = EMPTY_PROGRESS()
            viewController.onData(false)
            viewController.onEmptyProgress(true)
            loadPage(startDate)
        }

        override fun refresh() {
            currentState = REFRESH()
            viewController.onRefreshProgress(true)
            loadPage(startDate)
        }

        override fun release() {
            currentState = RELEASED()
            disposable?.dispose()
        }
    }

    private inner class RELEASED : State<T>
}
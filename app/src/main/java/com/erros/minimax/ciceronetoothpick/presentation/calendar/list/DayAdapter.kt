package com.erros.minimax.ciceronetoothpick.presentation.calendar.list

import android.support.v7.util.DiffUtil
import com.erros.minimax.ciceronetoothpick.data.model.Day
import com.erros.minimax.ciceronetoothpick.presentation.base.delegates.DiffCallback
import com.erros.minimax.ciceronetoothpick.presentation.base.delegates.EmptyAdapterDelegate
import com.erros.minimax.ciceronetoothpick.presentation.base.delegates.ErrorAdapterDelegate
import com.erros.minimax.ciceronetoothpick.presentation.base.delegates.ProgressAdapterDelegate
import com.erros.minimax.ciceronetoothpick.presentation.model.ListItem
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

/**
 * Created by milkman on 06.02.18.
 */
class DayAdapter(retryListener: () -> Unit) : ListDelegationAdapter<MutableList<Any>>() {

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(ProgressAdapterDelegate())
        delegatesManager.addDelegate(EmptyAdapterDelegate())
        delegatesManager.addDelegate(ErrorAdapterDelegate(retryListener))
        delegatesManager.addDelegate(DayAdapterDelegate())
    }

    fun setData(days: List<Day>) {
        val oldData = items.toList()
        val progress = isProgress()

        items.clear()
        items.addAll(days)
        if (progress) items.add(ListItem.ProgressItem())

        DiffUtil.calculateDiff(DiffCallback(items, oldData), false)
                .dispatchUpdatesTo(this)
    }

    fun showProgress(isVisible: Boolean) {
        val oldData = items.toList()
        val currentProgress = isProgress()

        if (isVisible && !currentProgress) items.add(ListItem.ProgressItem())
        else if (!isVisible && currentProgress) items.remove(items.last())

        DiffUtil.calculateDiff(DiffCallback(items, oldData), false)
                .dispatchUpdatesTo(this)
    }

    fun showEmpty(show: Boolean) {
        if (show) {
            val oldData = items.toList()
            items.clear()
            items.add(ListItem.EmptyItem())
            DiffUtil.calculateDiff(DiffCallback(items, oldData), false)
                    .dispatchUpdatesTo(this)
        } else {
            if (items.isNotEmpty() && items[0] is ListItem.EmptyItem) {
                val oldData = items.toList()
                items.clear()
                DiffUtil.calculateDiff(DiffCallback(items, oldData), false)
                        .dispatchUpdatesTo(this)
            }
        }
    }

    fun showError(show: Boolean) {
        if (show) {
            val oldData = items.toList()
            items.clear()
            items.add(ListItem.ErrorItem())
            DiffUtil.calculateDiff(DiffCallback(items, oldData), false)
                    .dispatchUpdatesTo(this)
        } else {
            if (items.isNotEmpty() && items[0] is ListItem.ErrorItem) {
                val oldData = items.toList()
                items.clear()
                DiffUtil.calculateDiff(DiffCallback(items, oldData), false)
                        .dispatchUpdatesTo(this)
            }
        }
    }

    private fun isProgress() = items.isNotEmpty() && items.last() is ListItem.ProgressItem

}
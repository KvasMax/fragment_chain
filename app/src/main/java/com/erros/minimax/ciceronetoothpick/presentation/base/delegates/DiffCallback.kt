package com.erros.minimax.ciceronetoothpick.presentation.base.delegates

import android.support.v7.util.DiffUtil

/**
 * Created by Konstantin Tskhovrebov (aka @terrakok) on 04.01.18.
 */
class DiffCallback(
        private val newData: List<Any>,
        private val oldData: List<Any>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldData.size
    override fun getNewListSize() = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldData[oldItemPosition] == newData[newItemPosition]
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldData[oldItemPosition] == newData[newItemPosition]
}
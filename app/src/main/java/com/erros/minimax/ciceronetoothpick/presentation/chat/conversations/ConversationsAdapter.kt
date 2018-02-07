package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import android.support.v7.util.DiffUtil
import com.erros.minimax.ciceronetoothpick.data.model.Person
import com.erros.minimax.ciceronetoothpick.presentation.base.delegates.DiffCallback
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

/**
 * Created by milkman on 07.02.18.
 */
class ConversationsAdapter : ListDelegationAdapter<MutableList<Any>>() {

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(PersonAdapterDelegate())
    }

    fun setData(persons: List<Person>) {
        val oldData = items.toList()
        items.clear()
        items.addAll(persons)

        dispatchChanges(items, oldData)
    }


    private fun dispatchChanges(newData: List<Any>, oldData: List<Any>) {
        DiffUtil.calculateDiff(DiffCallback(newData, oldData), false)
                .dispatchUpdatesTo(this)
    }
}
package com.erros.minimax.ciceronetoothpick.presentation.base.delegates

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.extension.inflate
import com.erros.minimax.ciceronetoothpick.presentation.model.ListItem
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

/**
 * Created by milkman on 06.02.18.
 */
class ProgressAdapterDelegate : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ProgressAdapterDelegate.ProgressViewHolder(parent.inflate(R.layout.item_progress))

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = items[position] is ListItem.ProgressItem

    override fun onBindViewHolder(items: MutableList<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {}

    private class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
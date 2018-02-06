package com.erros.minimax.ciceronetoothpick.presentation.calendar.list

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.data.model.Day
import com.erros.minimax.ciceronetoothpick.extension.inflate
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.item_day.view.*

/**
 * Created by milkman on 06.02.18.
 */
class DayAdapterDelegate : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = DayViewHolder(parent)

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = items[position] is Day

    override fun onBindViewHolder(items: MutableList<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        (holder as DayViewHolder).bind(items[position] as Day)
    }

    private class DayViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view.inflate(R.layout.item_day)) {
        fun bind(day: Day) {
            itemView.dateTextView.text = day.date
            itemView.titleTextView.text = day.celebrations.joinToString(separator = "\n") { it.title }
            var color = day.celebrations.first().colour
            if (color == "violet") {
                color = "purple"
            }
            itemView.colorIndicatorView.setBackgroundColor(Color.parseColor(color))
        }
    }
}